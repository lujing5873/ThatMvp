package com.nhcz500.base.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import pers.nchz.thatmvp.kotlin.view.IRvView;

public class BaseVH<T extends IRvView> extends BaseViewHolder {
    public    T view;
    public BaseVH(@NonNull T view) {
        super(view.getRootView());
        this.view=view;
    }
}
