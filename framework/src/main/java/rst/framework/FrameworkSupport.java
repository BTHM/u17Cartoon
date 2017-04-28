package rst.framework;

import android.support.v4.app.FragmentActivity;

import java.util.List;

import rst.framework.enums.SessionState;
import rst.framework.model.viewmodel.LocationViewModel;

/**
 * Created by iceman on 16/8/9 16:24
 * 邮箱：xubin865@pingan.com.cn
 * <p/>
 * app与framework通信的接口
 */
public interface FrameworkSupport {
    /**
     * 账号在别处登录
     */
    void onSessionInvaild();

    /**
     * 医保卡被另一个手机绑定
     */
    void onCardInValid();

    /**
     * 获取登录状态
     *
     * @return
     */
    SessionState getSessionState();

    /**
     * 获取城市zoneCode
     *
     * @return
     */
    String getZoneCode();

    /**
     * 获取城市zoneName
     *
     * @return
     */
    String getZoneName();

    /**
     * 获取用户phoneNumber
     *
     * @return
     */
    String getPhoneNumber();

    /**
     * 获得token
     *
     * @return
     */
    String getToken();
    /**
     * 页面间的跳转
     * @param activity activity
     * @param id 模块ID
     * @param params
     */
    void goToActivity(FragmentActivity activity, int id, String params);

    /**
     * 获取应用的apptype
     *
     * @return
     */
    String getAppType();

    /**
     * 获取应用cms的apptype
     *
     * @return
     */
    String getCmsAppType();

    /**
     * 获取开放城市列表
     *
     * @return
     */
    List<LocationViewModel> getOpenCityList();

}
