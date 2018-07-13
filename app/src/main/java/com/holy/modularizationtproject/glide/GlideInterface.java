package com.holy.modularizationtproject.glide;

import android.support.v7.widget.RecyclerView;

/**
 * Created by holywang on 2018/7/10.
 */

public interface GlideInterface {

    void onItemMove(int from, int to, RecyclerView.ViewHolder holder);
    void onItemDismiss(int position);

}
