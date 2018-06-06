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

import com.holy.modularizationtproject.R;
import com.holy.modularizationtproject.component.view.MoveButton;

public class RootActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private MoveButton moveButton;
    private float statusBarHeight1 = -1;
    private float startPostionX,startPostionY;
    private long startTime = 0;

    private float moveBtnWidth,moveBtnHeight;
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


        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        moveButton = new MoveButton(this);
        moveBtnWidth = moveButton.bitmapX;
        moveBtnHeight = moveButton.bitmapY;
        startPostionX = moveButton.getX();
        startPostionY = moveButton.getY();

        ConstraintLayout rootLayout = findViewById(R.id.container);
        rootLayout.addView(moveButton);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            startTime = System.currentTimeMillis();
        }
        if(event.getAction()==MotionEvent.ACTION_MOVE) {
            dealMoveButton(event);
        }

        if(event.getAction()==MotionEvent.ACTION_UP) {

            float x = event.getX()-startPostionX;
            float y = event.getY()-startPostionY;

            startPostionX = event.getX();
            startPostionY = event.getY();

            if(Math.abs(x)<50 && Math.abs(y)<50){
                if (System.currentTimeMillis() - startTime <200){
                    Toast.makeText(this, "it is click", Toast.LENGTH_SHORT).show();
                }

            }
            startTime = 0;

        }
        return super.onTouchEvent(event);
    }

    private void dealMoveButton(MotionEvent event){
        if(statusBarHeight1 != -1) {
            moveButton.bitmapX = event.getRawX()-moveBtnWidth/2;
            moveButton.bitmapY = event.getRawY() - statusBarHeight1 - moveBtnHeight/2;
            moveButton.invalidate();
        }
    }

    @Override
    protected void onDestroy() {
        moveButton.recycle();
        super.onDestroy();
    }
}
