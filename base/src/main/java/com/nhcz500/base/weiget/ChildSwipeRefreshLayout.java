package com.nhcz500.base.weiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.jetbrains.annotations.NotNull;

public class ChildSwipeRefreshLayout extends SwipeRefreshLayout {

    private int  startX = 0;
    private int startY= 0;
    private boolean beginScroll = false ;//是否开始滑动

    public ChildSwipeRefreshLayout(@NonNull @NotNull Context context) {
        super(context);
    }

    public ChildSwipeRefreshLayout(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case  MotionEvent.ACTION_DOWN:
                startX= (int) ev.getX();
                startY= (int) ev.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int x= (int) Math.abs(ev.getX()-startX);
                int y= (int) Math.abs(ev.getY()-startY);
                if(x>y){
                    if (!beginScroll){
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }else{
                    beginScroll = true;
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                beginScroll=false;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}