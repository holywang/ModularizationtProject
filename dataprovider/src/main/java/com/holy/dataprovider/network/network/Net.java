package com.holy.dataprovider.network.network;

/**
 * Created by DR on 2018/6/6.
 */

public interface Net {

    <T> T network(String uri,Class<T> clz);
}
