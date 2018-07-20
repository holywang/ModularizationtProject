package com.holy.modularizationtproject.glide.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.holy.modularizationtproject.R;

/**
 * Created by holywang on 2018/7/17.
 */

public class ImageGlideHolder extends RecyclerView.ViewHolder{

    public ImageView dataImage;

    public ImageGlideHolder(View itemView) {
        super(itemView);
        dataImage = itemView.findViewById(R.id.data_image);
    }

}
