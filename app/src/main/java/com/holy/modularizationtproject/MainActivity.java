package com.holy.modularizationtproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.holy.modularizationtproject.component.anim.ShackAnim;
import com.holy.modularizationtproject.main.RootActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ImageView splashView ;

    private Handler splashHandler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splashView = findViewById(R.id.splash_view);

        String splashGif = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2652687836,2319339229&fm=27&gp=0.jpg";

        Glide.with(this).load(splashGif).asGif().placeholder(R.mipmap.ic_launcher_round).into(splashView);

        final ShackAnim tremble = new ShackAnim();
        tremble.setDuration(1000);
        tremble.setRepeatCount(2);
        splashView.setAnimation(tremble);

        splashHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg != null && msg.what == 0){
                    Intent it = new Intent();
                    it.setClass(MainActivity.this, RootActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        };

        new Thread(splashThread).start();
    }

    private Runnable splashThread = new Runnable() {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(1000);
                }
                Message msg = Message.obtain();
                msg.what = 0;
                splashHandler.sendMessage(msg);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
