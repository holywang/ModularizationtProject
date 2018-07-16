package com.holy.modularizationtproject.glide.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.holy.dataprovider.network.bean.GankIOAndroidBean;
import com.holy.modularizationtproject.R;
import com.holy.modularizationtproject.glide.GlideInterface;
import com.holy.modularizationtproject.glide.WebActivity;

import java.util.List;

/**
 * Created by holywang on 2018/7/9.
 */

public class GlideAdapter extends RecyclerView.Adapter<GlideHolder> implements GlideInterface {

    private List<GankIOAndroidBean.ResultsBean> list;
    protected LayoutInflater mInflater;
    private Context context;

    public GlideAdapter(List<GankIOAndroidBean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public GlideHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.glide_item, null);
        return new GlideHolder(v);
    }

    @Override
    public void onBindViewHolder(GlideHolder holder, final int position) {
        holder.title.setText(list.get(position).getWho());
        holder.decs.setText(list.get(position).getDesc());
        if (list.get(position).getImages() != null && list.get(position).getImages().size() != 0) {
            Glide.with(context).load(list.get(position).getImages().get(0)).asGif().error(R.mipmap.ic_launcher_round).into(holder.descImage);
        } else {
            Glide.with(context).load(R.mipmap.ic_launcher_round).into(holder.descImage);
        }
        holder.deleteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlideAdapter.this.notifyItemRemoved(position);
            }
        });

        holder.deleteLayout.setVisibility(View.INVISIBLE);

        holder.textItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(view,position);
            }
        });

        holder.descImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(view,position);
            }
        });
    }

    private void onItemClick(View view,int position) {
        Intent it = new Intent();
        it.setClass(context, WebActivity.class);
        it.putExtra("data",list.get(position));
        context.startActivity(it);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemMove(int from, int to, RecyclerView.ViewHolder holder) {
        if (to - from > 150) {
            ((GlideHolder) holder).deleteLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemDismiss(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }
}
