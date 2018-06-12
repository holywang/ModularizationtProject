package com.holy.modularizationtproject;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by DR on 2018/6/12.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initView();
        addListener();
        afterSetting();
    }
    protected abstract void setContentView();
    protected abstract void initView();
    protected abstract void addListener();
    protected abstract void afterSetting();
}
