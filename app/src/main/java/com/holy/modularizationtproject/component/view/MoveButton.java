package com.holy.modularizationtproject.component.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.holy.modularizationtproject.R;

/**
 * Created by DR on 2018/6/6.
 */

public class MoveButton extends View {

    public float bitmapX;
    public float bitmapY;
    private Bitmap bitmap;

    public MoveButton(Context context) {
        super(context);

        bitmapX=200;
        bitmapY=300;
    }

    public MoveButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MoveButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint =new Paint();

//        bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_home_black_24dp);

        Drawable vetor = this.getResources().getDrawable(R.mipmap.ic_launcher_round,null);
        bitmap = Bitmap.createBitmap(vetor.getIntrinsicWidth(),vetor.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas cvs = new Canvas(bitmap);
        vetor.setBounds(0,0,cvs.getWidth(),cvs.getHeight());
        vetor.draw(cvs);
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, bitmapX, bitmapY, paint);
        }


    }

    public void recycle(){
       if( bitmap.isRecycled()) bitmap.recycle();
    }
}
