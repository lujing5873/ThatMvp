package com.nhcz500.base.weiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.jetbrains.annotations.NotNull;

public class ParentSwipeRefreshLayout extends SwipeRefreshLayout {

    private int  startX = 0;
    private int startY= 0;
    // 记录viewPager是否拖拽的标记
    private boolean mIsVpDrag;

    public ParentSwipeRefreshLayout(@NonNull @NotNull Context context) {
        super(context);
    }

    public ParentSwipeRefreshLayout(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case  MotionEvent.ACTION_DOWN:
                startX= (int) ev.getX();
                startY= (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 如果viewpager正在拖拽中，那么不拦截它的事件，直接return false；
                if(mIsVpDrag) {
                    return false;
                }

                int x= (int) Math.abs(ev.getX()-startX);
                int y= (int) Math.abs(ev.getY()-startY);
                if(x>=y){
                    mIsVpDrag=true;
                    return false;
                }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // 初始化标记
                mIsVpDrag = false;
                break;

        }
        return super.onInterceptTouchEvent(ev);
    }

}