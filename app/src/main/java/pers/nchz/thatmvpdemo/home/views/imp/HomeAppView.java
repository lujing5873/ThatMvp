package pers.nchz.thatmvpdemo.home.views.imp;

import android.content.Context;
import android.os.Bundle;

import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.home.presenters.MainPresenter;
import pers.nchz.thatmvpdemo.home.views.IHomeView;

public class HomeAppView extends ThatBaseView<MainPresenter> implements IHomeView {
    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.view_home_appbar;
    }

    @Override
    public Class<? extends IThatBaseView> getInterface() {
        return IHomeView.class;
    }
}
