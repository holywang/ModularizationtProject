package com.holy.modularizationtproject.path;

import com.holy.modularizationtproject.BaseActivity;
import com.holy.modularizationtproject.R;
import com.holy.modularizationtproject.component.view.SpiderNetView;

public class PathActivity extends BaseActivity {

    private SpiderNetView spiderNetView;

    private double[] data = {};
    private String[] title = {};

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_path);
    }

    @Override
    protected void initView() {
        spiderNetView = findViewById(R.id.spider_net_view);
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void afterSetting() {
        data = new double[]{10,30,15,60,20,5,90,45};
        title = new String[data.length];
        spiderNetView.setCount(8);
        spiderNetView.setData(data);
        for (int i = 0; i < data.length; i++) {
            title[i] = ""+(int)data[i];
        }
        spiderNetView.setTitle(title);
    }
}
