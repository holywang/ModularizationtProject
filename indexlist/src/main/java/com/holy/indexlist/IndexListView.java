package com.holy.indexlist;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.holy.indexlist.views.RightScrollIndexView;

/**
 * Created by DR on 2018/6/7.
 */

public class IndexListView extends RelativeLayout {

    private RightScrollIndexView indexView;

    public IndexListView(Context context) {
        super(context);

    }

    public IndexListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IndexListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public IndexListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_index_list,null);


    }
}
