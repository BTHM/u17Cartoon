package com.pingan.u17.net;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */

public class ObjectLoader {
    /**
     *
     * @param observable
     * @param
     * @return
     */
    protected  Observable observe(Observable observable){
        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}