package com.holy.modularizationtproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.holy.dataprovider.network.NetworkHelper;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkHelper helper = new NetworkHelper();

    }
}
