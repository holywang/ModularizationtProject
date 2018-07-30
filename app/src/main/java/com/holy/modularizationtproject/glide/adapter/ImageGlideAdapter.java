package com.holy.modularizationtproject.glide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.holy.dataprovider.network.bean.GankIOFuliBean;
import com.holy.modularizationtproject.R;
import com.holy.modularizationtproject.glide.interfaces.ImageGlideInterface;

import java.util.List;

/**
 * Created by holywang on 2018/7/17.
 */

public class ImageGlideAdapter extends RecyclerView.Adapter<ImageGlideHolder> implements ImageGlideInterface {

    private View mHeaderView;
    private View mFooterView;

    public static final int TYPE_HEADER = 0;//说明是带有Header的
    public static final int TYPE_FOOTER = 1;//说明是带有Footer的
    public static final int TYPE_NORMAL = 2;

    private Context context;
    private List<GankIOFuliBean.Results> dataList;

    public ImageGlideAdapter(Context context, List<GankIOFuliBean.Results> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        if (position == getItemCount() - 1) {
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public ImageGlideHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new ImageGlideHolder(mHeaderView);
        }

        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new ImageGlideHolder(mFooterView);
        }

        View view = LayoutInflater.from(context).inflate(R.layout.image_glide_item, parent, false);

        return new ImageGlideHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageGlideHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            Glide.with(context).load(dataList.get(position).getUrl()).into(holder.dataImage);
            return;
        } else if (getItemViewType(position) == TYPE_HEADER) {
            return;
        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    @Override
    public void addHeaderView(View headerView) {
        this.mHeaderView = headerView;
        notifyItemInserted(0);
    }

    @Override
    public void addFooterView(View footerView) {
        this.mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }


    public void addMore(List<GankIOFuliBean.Results> list){
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    public void refresh(List<GankIOFuliBean.Results> list){
        dataList = list;
        notifyDataSetChanged();
    }
}
