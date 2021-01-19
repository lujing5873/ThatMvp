package pers.nchz.thatmvpdemo.home.adpters;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import pers.nchz.thatmvpdemo.home.data.HomeData;

public class HomeAdapter extends BaseQuickAdapter<HomeData, BaseViewHolder> {
    public HomeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HomeData homeData) {

    }
}
