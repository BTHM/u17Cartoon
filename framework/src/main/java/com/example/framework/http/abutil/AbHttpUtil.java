/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.framework.http.abutil;

import android.content.Context;

import com.example.framework.http.request.AbHttpClient;
import com.example.framework.http.request.AbRequestParams;
import com.example.framework.http.response.AbBinaryHttpResponseListener;
import com.example.framework.http.response.AbFileHttpResponseListener;
import com.example.framework.http.response.AbHttpResponseListener;

import org.json.JSONObject;

import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * © 2012 amsoft.cn
 * 名称：AbHttpUtil.java 
 * 描述：Http执行工具类，可处理get，post，以及异步处理文件的上传下载
 *
 * @author 还如一梦中
 * @version v1.0
 * @date：2013-10-22 下午4:15:52
 */
public class AbHttpUtil {

	/** 实例化单例对象. */
	private AbHttpClient mClient = null;

	/** 工具类单例. */
	private static AbHttpUtil mAbHttpUtil = null;


	/**
	 * 描述：获取实例.
	 *
	 * @param context the context
	 * @return single instance of AbHttpUtil
	 */
	public static AbHttpUtil getInstance(Context context){
		if (null == mAbHttpUtil){
			mAbHttpUtil = new AbHttpUtil(context);
		}
		return mAbHttpUtil;
	}

	/**
	 * 初始化AbHttpUtil.
	 *
	 * @param context the context
	 */
	private AbHttpUtil(Context context) {
		super();
		this.mClient = new AbHttpClient(context);
	}

	/**
	 * 描述：无参数的get请求.
	 *
	 * @param url the url
	 * @param responseListener the response listener
	 */
	public void get(String url, AbHttpResponseListener responseListener) {
		mClient.get(url,null,responseListener);
	}
	/**
	 * 描述：无参数的get请求.
	 *
	 * @param url the url
	 * @param responseListener the response listener
	 */
	public void getWebData(String url, AbHttpResponseListener responseListener) {
		//mClient.getWebData(url,new AbRequestParams("xwalk","7价肺炎球菌结合疫苗"),responseListener);
	}

	/**
	 * 描述：带参数的get请求.
	 *
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void get(String url, AbRequestParams params,
					AbHttpResponseListener responseListener) {
		mClient.get(url, params, responseListener);
	}

	/**
	 * 描述：带请求头和参数的get请求.
	 *
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void get(String url, AbRequestParams params,
					AbHttpResponseListener responseListener, Map<String,String> header) {
		 //mClient.get(url, params, responseListener, header);
	}

	/**
	 *
	 * 描述：下载数据使用，会返回byte数据(下载文件或图片).
	 *
	 * @param url the url
	 * @param responseListener the response listener
	 */
	public void get(String url, AbBinaryHttpResponseListener responseListener) {
		mClient.get(url,null,responseListener);
	}

	/**
	 * 描述：文件下载的get.
	 *
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void get(String url, AbRequestParams params,
					AbFileHttpResponseListener responseListener) {
		mClient.get(url, params, responseListener);
	}

	/**
	 * 描述：无参数的post请求.
	 *
	 * @param url the url
	 * @param responseListener the response listener
	 */
	public void post(String url, AbHttpResponseListener responseListener) {
		//mClient.post(url, null, responseListener);
	}

	/**
	 * 描述：带参数的post请求.
	 *
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void post(String url, AbRequestParams params,
					 AbHttpResponseListener responseListener) {
		//mClient.post(url, params, responseListener);
	}
	/**
	 * 描述：带参数的文件上传请求
	 *
	 */
	public void uploadPost(String url, AbRequestParams params,
						   AbHttpResponseListener responseListener,final Map<String, String> header) {
		//mClient.dopost(url, params, responseListener,header);
	}
	/**
	 * 描述：带参数的delete请求.
	 *
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void delete(String url, JSONObject params,
					   AbHttpResponseListener responseListener) {
		//mClient.delete(url, params,responseListener);
	}

	/**
	 * 描述：文件下载的post.
	 *
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void post(String url, AbRequestParams params,
					 AbFileHttpResponseListener responseListener) {
		//mClient.post(url, params, responseListener);
	}

	/**
	 * 描述：带参数的JSONPost请求.
	 *
	 * @param url the url
	 * @param params the params
	 * @param responseListener the response listener
	 */
	public void post(String url, JSONObject params,
					 AbHttpResponseListener responseListener) {
		//mClient.postJSON(url, params, responseListener);
	}

	/**
	 * 描述：设置连接超时时间(第一次请求前设置).
	 *
	 * @param timeout 毫秒
	 */
	public void setTimeout(int timeout) {
	//	mClient.setTimeout(timeout);
	}

	/**
	 * 打开ssl 自签名(第一次请求前设置).
	 * @param enabled
	 */
	public void setEasySSLEnabled(boolean enabled){
		//mClient.setOpenEasySSL(enabled);
	}

	/**
	 * 设置编码(第一次请求前设置).
	 * @param encode
	 */
	public void setEncode(String encode) {
		//mClient.setEncode(encode);
	}

	/**
	 * 设置用户代理(第一次请求前设置).
	 * @param userAgent
	 */
	public void setUserAgent(String userAgent) {
		//mClient.setUserAgent(userAgent);
	}

	/**
	 * 设置重试机制
	 * @param defaultMaxRetries
     */
	public void setDefaultMaxRetries(int defaultMaxRetries) {
		//mClient.setDefaultMaxRetries(defaultMaxRetries);
	}

	/**
	 * 关闭HttpClient
	 * 当HttpClient实例不再需要是，确保关闭connection manager，以释放其系统资源  
	 */
	public void shutdownHttpClient(){
		if(mClient != null){
			//mClient.shutdown();
		}
	}

}
