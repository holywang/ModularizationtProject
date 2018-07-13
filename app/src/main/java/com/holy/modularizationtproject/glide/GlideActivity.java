package com.holy.modularizationtproject.glide;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.holy.dataprovider.DataCallback;
import com.holy.dataprovider.DataProvider;
import com.holy.dataprovider.network.bean.GankIOAndroidBean;
import com.holy.modularizationtproject.BaseActivity;
import com.holy.modularizationtproject.R;
import com.holy.modularizationtproject.glide.adapter.GlideAdapter;
import com.holy.modularizationtproject.glide.adapter.GlideHolder;

import java.util.List;

public class GlideActivity extends BaseActivity {

    private RecyclerView glide_recycler_list;
    private int number = 10, page = 1;
    private List<GankIOAndroidBean.ResultsBean> list;
    private GlideAdapter adapter;
    private ItemTouchHelper.Callback callback;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_glide);
    }

    @Override
    protected void initView() {
        glide_recycler_list = findViewById(R.id.glide_recycler_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        glide_recycler_list.setLayoutManager(layoutManager);
        glide_recycler_list.addItemDecoration(itemDecoration);
        glide_recycler_list.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            private float startX, startY;
            private float endX, endY;

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = e.getX();
                        startY = e.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:


                        if (e.getX() - startX > 20) {

                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        endX = e.getX();
                        endY = e.getY();
                        break;
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });



    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void afterSetting() {

        getData();


    }

    private void getData() {
        DataProvider.build(DataProvider.NEED_NETWORK)
                .getAndroidData(number, page, new DataCallback<GankIOAndroidBean>() {

                    @Override
                    public void getData(GankIOAndroidBean data) {
                        list = data.getResults();

                        adapter = new GlideAdapter(list, getApplicationContext());

                        glide_recycler_list.setAdapter(adapter);

                        callback = new GlideItemTouchHelperCallback(adapter);

                        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);

                        itemTouchHelper.attachToRecyclerView(glide_recycler_list);
                    }

                    @Override
                    public void error(Throwable e) {

                    }

                    @Override
                    public void complete() {

                    }
                });
    }

    private RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            Paint paint = new Paint();
            paint.setARGB(90, 12, 23, 32);

            c.drawLine(0, parent.getY(), parent.getX(), parent.getY(), paint);
        }
    };




}

class GlideItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private GlideAdapter adapter;

    GlideItemTouchHelperCallback(GlideAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0,ItemTouchHelper.START | ItemTouchHelper.END);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition(),viewHolder);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setScrollX(0);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ((GlideHolder) viewHolder).deleteLayout.getLayoutParams();
        params.width = 150;
        params.height = 150;
    }
}
