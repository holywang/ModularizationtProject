package com.holy.modularizationtproject.glide;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.holy.dataprovider.network.bean.GankIOAndroidBean;
import com.holy.modularizationtproject.BaseActivity;
import com.holy.modularizationtproject.R;

public class WebActivity extends BaseActivity {

    private WebView webView;
    private ProgressBar webViewProgress;
    private Intent data;
    private String url;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_web);
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.web_view);
        webViewProgress = findViewById(R.id.web_view_progress);
    }

    @Override
    protected void addListener() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void afterSetting() {
        data = getIntent();
        url = ((GankIOAndroidBean.ResultsBean)data.getSerializableExtra("data")).getUrl();
        webView.loadUrl(url);
        webView.setWebViewClient(webViewClient);
        WebSettings setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);
        webView.setWebChromeClient(webChromeClient);

    }

    private WebViewClient webViewClient = new WebViewClient(){
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(url);
            return true;
        }
    };

    private WebChromeClient webChromeClient = new WebChromeClient(){

        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            if(newProgress==100){
                webViewProgress.setVisibility(View.GONE);//加载完网页进度条消失
            }
            else{
                webViewProgress.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                webViewProgress.setProgress(newProgress);//设置进度值
            }

        }

    };
}
