package com.holy.modularizationtproject.component.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Binder;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.holy.modularizationtproject.R;

/**
 * Created by DR on 2018/6/12.
 */

public class SpiderNetView extends View {

    private int count ;//蜘蛛网层数
    private String[] titles;
    private double[] data;

    private float radius; //半径
    private int centerX,centerY;//中心X,Y
    private float angle = (float) (Math.PI*2/count);

    private double maxValue = 100;

    private Paint netPaint;                //雷达区画笔
    private Paint valuePaint;               //数据区画笔
    private Paint textPaint;                //文本画笔

    private Handler handler;
    @SuppressLint("Recycle")
    public SpiderNetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray s = getContext().obtainStyledAttributes(attrs, R.styleable.SpiderNetView);
        count = s.getInteger(R.styleable.SpiderNetView_count,6);
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTitle(String[] titles) {
        this.titles = titles;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        setPaint();


        drawPolygon(canvas);
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w)/2*0.9f;
        //中心坐标
        centerX = w/2;
        centerY = h/2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void setPaint(){
        netPaint = new Paint();
        netPaint.setColor(Color.RED);
        netPaint.setStyle(Paint.Style.STROKE);

        valuePaint = new Paint();
        valuePaint.setColor(Color.GRAY);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        valuePaint.setAlpha(255);

        textPaint = new Paint();
        textPaint.setTextSize(12);
        textPaint.setColor(Color.GREEN);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void drawPolygon(Canvas canvas){

        Path path = new Path();
        float r = radius/count;
        for (int i = 0; i < count+1 ; i++) {
            float curRadius = r * i;
            path.reset();
            for (int j = 0; j < count ; j++) {
                if (j==0){
                    path.moveTo(centerX+curRadius,centerY);
                }else{
                    float x = (float)(centerX+curRadius*Math.cos(angle*j));
                    float y = (float)(centerY+curRadius*Math.sin(angle*j));
                    path.lineTo(x,y);
                }
            }
            path.close();
            canvas.drawPath(path,netPaint);
        }

    }

    private void drawLines(Canvas canvas){
        Path path = new Path();
        for(int i=0;i<count;i++){
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX+radius*Math.cos(angle*i));
            float y = (float) (centerY+radius*Math.sin(angle*i));
            path.lineTo(x, y);
            canvas.drawPath(path, netPaint);
        }
    }

    private void drawText(Canvas canvas){
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for(int i=0;i<count;i++){
            float x = (float) (centerX+(radius+fontHeight/2)*Math.cos(angle*i));
            float y = (float) (centerY+(radius+fontHeight/2)*Math.sin(angle*i));
            if(angle*i>=0&&angle*i<=Math.PI/2){//第4象限
                canvas.drawText(titles[i], x,y,textPaint);
            }else if(angle*i>=3*Math.PI/2&&angle*i<=Math.PI*2){//第3象限
                canvas.drawText(titles[i], x,y,textPaint);
            }else if(angle*i>Math.PI/2&&angle*i<=Math.PI){//第2象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x-dis,y,textPaint);
            }else if(angle*i>=Math.PI&&angle*i<3*Math.PI/2){//第1象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x-dis,y,textPaint);
            }
        }
    }

    private void drawRegion(Canvas canvas){
        Path path = new Path();
        valuePaint.setAlpha(255);
        for(int i=0;i<count;i++){
            double percent = data[i]/maxValue;
            float x = (float) (centerX+radius*Math.cos(angle*i)*percent);
            float y = (float) (centerY+radius*Math.sin(angle*i)*percent);
            if(i==0){
                path.moveTo(x, centerY);
            }else{
                path.lineTo(x,y);
            }
            //绘制小圆点
            canvas.drawCircle(x,y,10,valuePaint);
        }
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        valuePaint.setAlpha(127);
        //绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }

}
