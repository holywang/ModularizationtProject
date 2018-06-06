package com.holy.dataprovider;

/**
 * Created by DR on 2018/6/6.
 */

public interface DataCallback<T> {
    void getData(T data);
    void error(Throwable e);
    void complete();
}
