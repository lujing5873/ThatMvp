package com.nhcz500.base.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import pers.nchz.thatmvp.kotlin.view.IRvView;

public class BaseVH<T extends IRvView> extends BaseViewHolder {
    public    T view;
    public BaseVH(@NonNull View view) {
        super(view);
    }

    public void setView(T view) {
        this.view = view;
    }
}
