package com.nhcz500.base.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.nhcz500.base.R;
import com.nhcz500.base.utils.DimenTransitionUtil;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import pers.nchz.thatmvp.kotlin.view.IRvView;
import pers.nchz.thatmvp.kotlin.view.RvView;


public abstract class RvListView<T> extends RvView<List<T>> {
    protected RecyclerView rv;
    @Override
    public int getRootLayoutId() {
        return R.layout.item_rv;
    }

    @Override
    public void initView() {
        rv=getView(R.id.item_rv);
        setAdapter();
    }

    protected abstract void setAdapter();
    @Override
    public IRvView<List<T>> setParent(@Nullable RecyclerView recyclerView) {
        rv.setRecycledViewPool(recyclerView.getRecycledViewPool());
        return super.setParent(recyclerView);
    }

    public void setMargin(int left, int top, int right, int bottom){
        RecyclerView.LayoutParams layoutParams= (RecyclerView.LayoutParams) root.getLayoutParams();
        layoutParams.topMargin= DimenTransitionUtil.dp2px(mActivity,top);
        layoutParams.leftMargin= DimenTransitionUtil.dp2px(mActivity,left);
        layoutParams.rightMargin= DimenTransitionUtil.dp2px(mActivity,right);
        layoutParams.bottomMargin= DimenTransitionUtil.dp2px(mActivity,bottom);
    }

}
