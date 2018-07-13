package com.holy.dataprovider.network;

import com.holy.dataprovider.network.bean.GankIOAndroidBean;
import com.holy.dataprovider.network.bean.GankIOFuliBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by DR on 2018/6/6.
 */

public interface GankIOService {

    @GET("Android/{number}/{page}")
    Observable<GankIOAndroidBean> getAndroidInfo(@Path("number")int number, @Path("page")int page);

    @GET("福利/{number}/{page}")
    Observable<GankIOFuliBean> getFuliInfo(@Path("number")int number, @Path("page")int page);
}
