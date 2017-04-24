package com.example.framework.http.request;

import android.text.TextUtils;

import com.example.framework.http.abutil.AbLogUtil;
import com.example.framework.http.response.AbHttpResponseListener;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/14
 */

public class AbRequestParams {
    /**
     * url参数.
     */
    protected ConcurrentHashMap<String, String> urlParams;

    /**
     * 文件参数.
     */
    protected ConcurrentHashMap<String, FileWrapper> fileParams;

    /**
     * 流常量.
     */
    private static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

    /**
     * 构造一个空的请求参数.
     */
    public AbRequestParams() {
        init();
    }

    /**
     * 用一个map构造请求参数.
     *
     * @param source the source
     */
    public AbRequestParams(Map<String, String> source) {
        init();

        for (Map.Entry<String, String> entry : source.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 用一个key和value构造请求参数.
     *
     * @param key   the key
     * @param value the value
     */
    public AbRequestParams(String key, String value) {
        init();
        put(key, value);
    }

    /**
     * 初始化.
     */
    private void init() {
        urlParams = new ConcurrentHashMap<String, String>();
        fileParams = new ConcurrentHashMap<String, FileWrapper>();
    }

    /**
     * 增加一对请求参数.
     *
     * @param key   the key
     * @param value the value
     */
    public void put(String key, String value) {
        if (key != null && value != null) {
            urlParams.put(key, value);
        }
    }


    /**
     * 增加一个文件域.
     *
     * @param key         the key
     * @param file        the file
     * @param contentType the content type of the file, eg. application/json
     */
    public void put(String key, File file, String contentType) {
        if (key != null && file != null) {
            fileParams.put(key, new FileWrapper(file, contentType));
        }
    }

    /**
     * 增加一个文件域.
     *
     * @param key  the key
     * @param file the file
     */
    public void put(String key, File file) {
        put(key, file, APPLICATION_OCTET_STREAM);
    }

    /**
     * 删除一个请求参数.
     *
     * @param key the key
     */
    public void remove(String key) {
        urlParams.remove(key);
        fileParams.remove(key);
    }

    /**
     * 描述：转换为参数字符串.
     *
     * @return the string
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }

        return result.toString();
    }

    /**
     * 获取参数列表.
     *
     * @return the params list
     */
    public List<BasicNameValuePair> getParamsList() {
        List<BasicNameValuePair> paramsList = new LinkedList<BasicNameValuePair>();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams
                .entrySet()) {
            paramsList.add(new BasicNameValuePair(entry.getKey(), entry
                    .getValue()));
        }
        return paramsList;
    }

    /**
     * 获取参数字符串.
     *
     * @return the param string
     */
    public String getParamString() {
        return URLEncodedUtils.format(getParamsList(), HTTP.UTF_8);
    }

    /**
     * 获取HttpEntity.
     *
     * @param responseListener the response listener
     * @return the entity
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public HttpEntity getEntity(AbHttpResponseListener responseListener)
            throws IOException {

        // 不包含文件的
        if (fileParams.isEmpty()) {
            return createFormEntity();
        } else {
            // 包含文件和参数的
            return createMultipartEntity(responseListener);
        }
    }

    /**
     * 获取UploadHttpEntity.
     *
     * @param responseListener the response listener
     * @return the entity
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public HttpEntity getuploadFileEntity(AbHttpResponseListener responseListener)
            throws IOException {
        // 处理意见反馈不传图片也必须要走包含文件和参数的
        if (!TextUtils.isEmpty(responseListener.moduleFlag) && responseListener.moduleFlag.equals("feedback")) {
            return createUploadMultipartEntity(responseListener);
        } else {
            // 不包含文件的
            if (fileParams.isEmpty()) {
                return createFormEntity();
            } else {
                // 包含文件和参数的
                return createUploadMultipartEntity(responseListener);
            }
        }
    }

    /**
     * 创建HttpEntity.
     *
     * @return the http entity
     */
    public HttpEntity createFormEntity() {
        try {
            return new UrlEncodedFormEntity(getParamsList(), HTTP.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 描述：创建文件域HttpEntity.
     *
     * @param responseListener the response listener
     * @return the http entity
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private HttpEntity createMultipartEntity(
            AbHttpResponseListener responseListener) throws IOException {
        AbMultipartEntity entity = new AbMultipartEntity(responseListener);

        // Add string params
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams
                .entrySet()) {
            entity.addPart(entry.getKey(), entry.getValue());
        }

        // Add file params
        for (ConcurrentHashMap.Entry<String, FileWrapper> entry : fileParams
                .entrySet()) {
            FileWrapper fileWrapper = entry.getValue();
            entity.addPart(entry.getKey(), fileWrapper.file,
                    fileWrapper.contentType);
        }

        return entity;
    }

    /**
     * 描述：创建文件上传域HttpEntity.
     */
    private HttpEntity createUploadMultipartEntity(
            AbHttpResponseListener responseListener) throws IOException {
        AbMultipartEntity entity = new AbMultipartEntity(responseListener, "-------------------------------7d273jdn23dd");

        // Add string params
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams
                .entrySet()) {
            entity.addPart(entry.getKey(), entry.getValue());
        }

        AbLogUtil.d("fileParams.size():" + fileParams.size());

        // Add file params
        for (ConcurrentHashMap.Entry<String, FileWrapper> entry : fileParams
                .entrySet()) {
            FileWrapper fileWrapper = entry.getValue();

            AbLogUtil.d("entry.getKey():" + entry.getKey() + " fileWrapper.file:" + fileWrapper.file.getName());
            entity.addPart("file", fileWrapper.file,
                    fileWrapper.contentType);
        }

        return entity;
    }

    /**
     * 获取url参数.
     *
     * @return the url params
     */
    public ConcurrentHashMap<String, String> getUrlParams() {
        return urlParams;
    }

    /**
     * 设置url参数.
     *
     * @param urlParams the url params
     */
    public void setUrlParams(ConcurrentHashMap<String, String> urlParams) {
        this.urlParams = urlParams;
    }


    /**
     * 文件类.
     */
    private static class FileWrapper {

        /**
         * 文件.
         */
        public File file;

        /**
         * 类型.
         */
        public String contentType;

        /**
         * 构造.
         *
         * @param file        the file
         * @param contentType the content type
         */
        public FileWrapper(File file, String contentType) {
            this.file = file;
            this.contentType = contentType;
        }
    }

    /**
     * 清空集合
     */
    public void clear() {
        urlParams.clear();
        fileParams.clear();
    }
}
