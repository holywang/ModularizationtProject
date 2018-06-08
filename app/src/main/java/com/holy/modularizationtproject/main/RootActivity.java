package com.holy.modularizationtproject.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.holy.indexlist.listener.OnSelectListener;
import com.holy.indexlist.views.RightScrollIndexView;
import com.holy.modularizationtproject.R;
import com.holy.modularizationtproject.component.view.MoveButton;

public class RootActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RightScrollIndexView indexView;
    private BottomNavigationView btnNavigation;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_root);

        mTextMessage = findViewById(R.id.message);

        indexView = findViewById(R.id.index);
        indexView.setSelectListener(new OnSelectListener() {
            @Override
            public void onSelect(int position, String text) {
                mTextMessage.setText(text);
            }
        });
        btnNavigation = findViewById(R.id.navigation);

        btnNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

//

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
