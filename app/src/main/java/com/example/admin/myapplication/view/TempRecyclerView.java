package com.example.admin.myapplication.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.admin.myapplication.utils.ScreenUtils;

/**
 * Created by Bruce .
 */

public class TempRecyclerView extends RecyclerView  {
    private static final float MAX_SCALE = 1.2f;
    private static final float MIN_SCALE = 0.8f;
    private static final float MAX_ALPHA = 1f;
    private static final float MIN_ALPHA = 0.5f;
    public final float SCREEN_WIDTH = ScreenUtils.getScreenWidth(this.getContext()) / 2;
    public float delta = SCREEN_WIDTH;
    private LinearLayoutManager manager;

    public TempRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public TempRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        manager = (LinearLayoutManager) layout;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
    }


    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        //实现中间变大两边缩小

        //首先计算percent
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {

            View rootView = this.getChildAt(i);
            //开始计算百分比
            RecyclerView.LayoutParams paramas = (RecyclerView.LayoutParams) rootView.getLayoutParams();
            paramas.rightMargin = 10;
            paramas.height = 200;


            float leftMargin = rootView.getLeft();
            float rightMargin = ScreenUtils.getScreenWidth(this.getContext()) - rootView.getRight();


            float percent = Math.min(leftMargin, rightMargin) / Math.max(leftMargin, rightMargin);

            //开始计算缩放比

            float scale = percent * (MAX_SCALE - MIN_SCALE) + MIN_SCALE;

            float alpha = percent * (MAX_ALPHA - MIN_ALPHA) + MIN_ALPHA;

            rootView.setScaleX(scale);
            rootView.setScaleY(scale);
            rootView.setAlpha(alpha);

        }
    }


    @Override
    public void onScrollStateChanged(int newState) {
        super.onScrollStateChanged(newState);
        if (newState == SCROLL_STATE_IDLE) {
            int childCount = manager.getChildCount();
            for (int index = 0; index < childCount; index++) {
                View rootView = this.getChildAt(index);
                float leftMargin = rootView.getLeft();
                float rightMargin = rootView.getRight();
                float childCenterX = (rightMargin + leftMargin) / 2;
                float deltaX = childCenterX - SCREEN_WIDTH;
                float centerChildX = Math.abs(deltaX);
                if (centerChildX <= Math.abs(delta)) {
                    delta = deltaX;
                }
            }
            this.smoothScrollBy((int) delta, 0);
            delta = SCREEN_WIDTH;

        }
    }

    //滑动到中间位置
    public void smoothScrollBy(View v) {
        float leftMargin = v.getLeft();
        float rightMargin = v.getRight();
        float centerX = (leftMargin + rightMargin) / 2;
        float deltaX = centerX - getResources().getDisplayMetrics().widthPixels / 2;
        if (Math.abs(deltaX) > 5) {
            //滚动到最中间
            smoothScrollBy((int) deltaX, 0);
        }
    }
}
