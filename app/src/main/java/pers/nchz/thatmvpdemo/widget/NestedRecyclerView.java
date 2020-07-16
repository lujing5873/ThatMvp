package pers.nchz.thatmvpdemo.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dell on 2018/3/12.
 */

public class NestedRecyclerView  extends RecyclerView {
    public NestedRecyclerView(Context context) {
        super(context);
    }

    public NestedRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow, int type) {
//        int[] window=new int[2];
//        getLocationInWindow(window);
//        System.out.println("windowyStart:"+window[1]);
//        System.out.println("consumed:"+consumed[1]);
//        System.out.println("offsetInWindow:"+offsetInWindow==null?">>>>>":offsetInWindow[1]);

        return  super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
    }
}

