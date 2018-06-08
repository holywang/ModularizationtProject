package com.holy.indexlist.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.holy.indexlist.R;
import com.holy.indexlist.listener.OnSelectListener;

/**
 * Created by DR on 2018/6/7.
 */

public class RightScrollIndexView extends View {

    /*绘制的列表导航字母*/
    private String words[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    /*字母画笔*/
    private Paint wordsPaint;
    /*字母背景画笔*/
    private Paint bgPaint;
    /*每一个字母的宽度*/
    private int itemWidth;
    /*每一个字母的高度*/
    private int itemHeight;
    /*手指按下的字母索引*/
    private int touchIndex = 0;


    /*添加滑动监听*/
    private OnSelectListener selectListener;

    public void setSelectListener(OnSelectListener selectListener) {
        this.selectListener = selectListener;
    }

    public int getTouchIndex() {
        return touchIndex;
    }

    public RightScrollIndexView(Context context) {
        super(context);
    }

    public RightScrollIndexView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RightScrollIndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RightScrollIndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemWidth = getMeasuredWidth();
        //使得边距好看一些
        int height = getMeasuredHeight() - 10;
        itemHeight = height / 27;
    }

    @Override
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        wordsPaint = setTextPaint();
        bgPaint = setBorderPaint();

        for (int i = 0; i < words.length; i++) {

            //判断是不是我们按下的当前字母
            if (touchIndex == i) {
                //绘制文字圆形背景
                wordsPaint.setColor(Color.RED);
            } else {
                wordsPaint.setColor(Color.GRAY);
            }
            //获取文字的宽高
            Rect rect = new Rect();
            wordsPaint.getTextBounds(words[i], 0, 1, rect);
            int wordWidth = rect.width();
            //绘制字母
            float wordX = itemWidth / 2 - wordWidth / 2;
            float wordY = itemWidth / 2 + i * itemHeight + 23;
            canvas.drawText(words[i], wordX, wordY, wordsPaint);
        }

    }

    /**
     * 边框画笔
     *
     * @return
     */
    private Paint setBorderPaint() {
        return new StreamlinePaint()
                .setColor(getContext().getColor(R.color.grey_999999))
                .setStyle(Paint.Style.FILL)
                .setStrokeWidth(2)
                .getPaint();
    }

    /**
     * 文字画笔
     *
     * @return
     */
    private Paint setTextPaint() {
        return new StreamlinePaint()
                .setColor(getContext().getColor(R.color.grey_999999))
                .setTextSize(46)
                .getPaint();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final float y = event.getY();// 点击y坐标
        // 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
        int pos = (int) (y / getHeight() * words.length);
        //由于总共的字符中包含最顶层的圆圈,所以indexContent中真正字符的位置应当在当前mChoose位置上减一
        touchIndex = pos;

        String text = words[touchIndex];
        if (selectListener != null) {
            selectListener.onSelect(touchIndex, text);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP: {
                touchIndex = -1;
            }
            break;
            default:
                break;
        }
        //强制重绘
        invalidate();
        return true;
    }
}
