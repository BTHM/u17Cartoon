package com.pingan.u17.base;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Author：liupeng on 2017/2/6 23:45
 * Address：liupeng264@pingan.com.cn
 */
public class U17Application extends Application {

    private static U17Application INSTANCE;
    private final static String ApiId="api88888888";
    private IWXAPI mWxapi;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        Fresco.initialize(this);
        regToWx();
    }

    public static U17Application getInstance() {
      return INSTANCE;
    }


    //注册到微信
   private void  regToWx(){
       mWxapi = WXAPIFactory.createWXAPI(INSTANCE, ApiId, true);
       mWxapi.registerApp(ApiId);
   }


}
