package pers.nchz.thatmvpdemo.home.views.imp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.ColorRes;

import pers.nchz.thatmvp.presenter.ThatBasePresenter;
import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.home.views.IStatusView;

public class StatusView extends ThatBaseView<ThatBasePresenter> implements IStatusView {

    @Override
    public void initView(Bundle savedInstanceState) {
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.view_status;
    }

    @Override
    public Class<? extends IThatBaseView> getInterface() {
        return null;
    }

    @Override
    public void setColor(@ColorRes int color) {
        rootView.setBackgroundResource(color);
    }
}
