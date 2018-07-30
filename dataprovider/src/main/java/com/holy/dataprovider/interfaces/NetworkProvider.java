package com.holy.dataprovider.interfaces;

import com.holy.dataprovider.DataCallback;
import com.holy.dataprovider.network.bean.GankIOAndroidBean;
import com.holy.dataprovider.network.bean.GankIOFuliBean;

/**
 * Created by DR on 2018/6/6.
 */

public interface NetworkProvider {

    void getAndroidData(int number,int page,DataCallback<GankIOAndroidBean> callback);

    void getFuliData(int number,int page,DataCallback<GankIOFuliBean> callback);

}
