package com.holy.dataprovider.network;

import com.holy.dataprovider.network.network.NetworkHelper;

/**
 * Created by DR on 2018/6/5.
 *
 */

public class NetHelper {

    private NetworkHelper networkHelper;
    private static NetHelper instance;
    private String url;


    private NetHelper(){
        networkHelper = new NetworkHelper();
    }

    public static NetHelper getInstance(){
        if(instance == null){
            synchronized(NetHelper.class){
                if(instance == null){
                    instance = new NetHelper ();
                }
            }
        }
        return instance;
    }

    public NetHelper setUrl(String url){
        this.url = url;
        return this;
    }

    public GankIOService setGankIOHelper(){
        return networkHelper.network(this.url,GankIOService.class);
    }




}
