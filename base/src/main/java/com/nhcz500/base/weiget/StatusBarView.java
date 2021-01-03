package com.nhcz500.base.weiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.nhcz500.base.utils.DimenTransitionUtil;


public class StatusBarView extends View {
    public StatusBarView(Context context) {
        super(context);
    }

    public StatusBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StatusBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec= MeasureSpec.makeMeasureSpec(DimenTransitionUtil.getStatusBarHeight(getContext()), MeasureSpec.AT_MOST);
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }
}
