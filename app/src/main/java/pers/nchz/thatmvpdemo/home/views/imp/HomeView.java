package pers.nchz.thatmvpdemo.home.views.imp;

import android.os.Bundle;

import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.home.presenters.MainPresenter;
import pers.nchz.thatmvpdemo.home.views.IHomeView;
import pers.nchz.thatmvpdemo.home.views.IMainView;

public class HomeView extends ThatBaseView<MainPresenter> implements IHomeView {
    @Override
    public void initView(Bundle savedInstanceState) {
        System.out.println("HomeView:"+presenter.getView(IMainView.class));
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.view_home;
    }

    @Override
    public Class<? extends IThatBaseView> getInterface() {
        return IHomeView.class;
    }
}
