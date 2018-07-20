package com.holy.modularizationtproject.glide;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.holy.dataprovider.DataCallback;
import com.holy.dataprovider.DataProvider;
import com.holy.dataprovider.network.bean.GankIOFuliBean;
import com.holy.modularizationtproject.BaseActivity;
import com.holy.modularizationtproject.R;
import com.holy.modularizationtproject.glide.adapter.ImageGlideAdapter;

import java.util.List;

public class ImageGlideActivity extends BaseActivity {

    private SwipeRefreshLayout onPullRefresh;

    private RecyclerView imageListView;

    private static int number = 10, page = 1;

    private List<GankIOFuliBean.Results> dataList;

    private ImageGlideAdapter adapter;
    private GridLayoutManager manager;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_image_glide);
    }

    @Override
    protected void initView() {
        onPullRefresh = findViewById(R.id.refresh_swipe_layout);
        imageListView = findViewById(R.id.image_recycler_list);
        manager = new GridLayoutManager(ImageGlideActivity.this,1);
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void afterSetting() {
        getData();
    }

    private void getData() {
        DataProvider.build(DataProvider.NEED_NETWORK)
                .getFuliData(number, page, new DataCallback<GankIOFuliBean>() {
                    @Override
                    public void getData(GankIOFuliBean data) {
                        dataList = data.getResults();
                        adapter = new ImageGlideAdapter(ImageGlideActivity.this,dataList);
                        adapter.addHeaderView(LayoutInflater.from(ImageGlideActivity.this).inflate(R.layout.image_glide_header, null, false));
                        adapter.addFooterView(LayoutInflater.from(ImageGlideActivity.this).inflate(R.layout.image_glide_footer, null, false));
                        imageListView.setLayoutManager(manager);
                        imageListView.setAdapter(adapter);

                    }

                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void complete() {

                    }
                });

    }

}
