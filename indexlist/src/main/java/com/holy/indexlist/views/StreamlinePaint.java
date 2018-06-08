package com.holy.indexlist.views;

import android.graphics.Paint;
import android.graphics.Shader;

/**
 * Created by DR on 2018/6/7.
 */

public class StreamlinePaint{

    private Paint paint;

    public StreamlinePaint(){
        paint = new Paint();
    }

    public Paint getPaint(){
        return paint;
    }

    //
    public StreamlinePaint setARGB(int a,int r,int g,int b) {
        paint.setARGB(a,r,g,b);
        return this;
    }

    public StreamlinePaint setAlpha(int alpha) {
        paint.setAlpha(alpha);
        return this;
    }

    public StreamlinePaint setColor(int color) {
        paint.setColor(color);
        return this;
    }

    public StreamlinePaint setAntiAlias(boolean aa) {
       paint.setAntiAlias(aa);
       return this;
    }

    public StreamlinePaint setDither(boolean dither) {
       paint.setDither(dither);
       return this;
    }

    public StreamlinePaint setShader(Shader shader) {
        paint.setShader(shader);
        return this;
    }

    public StreamlinePaint setShadowLayer(float radius ,float dx,float dy,int color) {
        paint.setShadowLayer(radius,dx,dy,color);
        return this;
    }

    public StreamlinePaint setStyle(Paint.Style style) {
        paint.setStyle(style);
        return this;
    }

    public StreamlinePaint setStrokeWidth(float width) {
        paint.setStrokeWidth(width);
        return this;
    }

    public StreamlinePaint setStrokeCap(Paint.Cap cap) {
        paint.setStrokeCap(cap);
        return this;
    }
    public StreamlinePaint setSrokeJoin(Paint.Join join) {
        paint.setStrokeJoin(join);
        return this;
    }

    //画文字系列
    public StreamlinePaint setTextSize(float textSize){
        paint.setTextSize(textSize);
        return this;
    }

}
