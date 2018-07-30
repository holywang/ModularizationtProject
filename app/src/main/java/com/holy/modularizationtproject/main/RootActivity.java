package com.holy.modularizationtproject.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.holy.modularizationtproject.R;
import com.holy.modularizationtproject.component.utils.AnimationUtil;
import com.holy.modularizationtproject.component.view.LeafLoadingView;
import com.holy.modularizationtproject.glide.GlideActivity;
import com.holy.modularizationtproject.glide.ImageGlideActivity;
import com.holy.modularizationtproject.path.PathActivity;
import com.holy.modularizationtproject.search.SearchActivity;

import java.util.Random;

public class RootActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView btnNavigation;
    private static final int REFRESH_PROGRESS = 0x10;
    private LeafLoadingView mLeafLoadingView;
    private int mProgress = 0;
    private View mFanView;
    private Button searchActivity,pathActivity,glideActivity,glideImageActivity;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_PROGRESS:
                    if (mProgress < 40) {
                        mProgress += 1;
                        // 随机800ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                new Random().nextInt(800));
                        mLeafLoadingView.setProgress(mProgress);
                    } else if(mProgress >= 100){
                        mTextMessage.setText("finish");
                        mLeafLoadingView.setProgress(mProgress);
                    }else {
                        mProgress += 1;
                        // 随机1200ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                new Random().nextInt(1200));
                        mLeafLoadingView.setProgress(mProgress);

                    }
                    break;

                default:
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_root);

        mTextMessage = findViewById(R.id.message);

        btnNavigation = findViewById(R.id.navigation);

        glideActivity = findViewById(R.id.glide_activity_button);

        searchActivity = findViewById(R.id.search_activity_button);

        pathActivity = findViewById(R.id.path_activity_button);

        glideImageActivity = findViewById(R.id.glide_image_activity_button);

        btnNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        searchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(RootActivity.this, SearchActivity.class);
                startActivity(it);
            }
        });

        pathActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(RootActivity.this, PathActivity.class);
                startActivity(it);
            }
        });

        glideActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent();
                it.setClass(RootActivity.this, GlideActivity.class);
                startActivity(it);
            }
        });
        mFanView = findViewById(R.id.fan_pic);
        RotateAnimation rotateAnimation = AnimationUtil.initRotateAnimation(false, 1500, true,
                Animation.INFINITE);
        mFanView.startAnimation(rotateAnimation);

        mLeafLoadingView = findViewById(R.id.leaf_loading);

        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS, 3000);

        glideImageActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent();
                it.setClass(RootActivity.this, ImageGlideActivity.class);
                startActivity(it);
            }
        });
    }

//


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
