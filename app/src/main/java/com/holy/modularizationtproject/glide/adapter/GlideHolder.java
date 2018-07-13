package com.holy.modularizationtproject.glide.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.holy.modularizationtproject.R;

/**
 * Created by holywang on 2018/7/9.
 */

public class GlideHolder extends RecyclerView.ViewHolder {

    public TextView title,decs,deleteText;
    public ImageView descImage;
    public FrameLayout deleteLayout;
    public GlideHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.glide_title);
        descImage = itemView.findViewById(R.id.glide_desc_image);
        decs = itemView.findViewById(R.id.glide_desc);
        deleteText = itemView.findViewById(R.id.tv_text);
        deleteLayout = itemView.findViewById(R.id.glide_right_layout);
    }

}
