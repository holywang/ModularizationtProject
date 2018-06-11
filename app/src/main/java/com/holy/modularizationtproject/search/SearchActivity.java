package com.holy.modularizationtproject.search;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.holy.indexlist.listener.OnSelectListener;
import com.holy.indexlist.views.RightScrollIndexView;
import com.holy.modularizationtproject.R;

public class SearchActivity extends AppCompatActivity {

    private RightScrollIndexView indexView;
    private TextView showSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showSelect = findViewById(R.id.show_select);
        indexView = findViewById(R.id.scroll_index);
        indexView.setSelectListener(new OnSelectListener() {
            @Override
            public void onSelect(int position, String text) {
                showSelect.setText(text);
            }
        });

        showSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

}
