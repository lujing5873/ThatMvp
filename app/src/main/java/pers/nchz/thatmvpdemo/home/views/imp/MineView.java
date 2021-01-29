package pers.nchz.thatmvpdemo.home.views.imp;

import android.content.Context;
import android.os.Bundle;

import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.home.presenters.MainPresenter;
import pers.nchz.thatmvpdemo.home.views.IHomeView;
import pers.nchz.thatmvpdemo.home.views.IMainView;
import pers.nchz.thatmvpdemo.home.views.IMineView;

public class MineView extends ThatBaseView<MainPresenter> implements IMineView {

    @Override
    public void initView(Bundle savedInstanceState) {
//        System.out.println("MineView:"+presenter.getView(IMainView.class));
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.view_mine;
    }

    @Override
    public Class<? extends IThatBaseView> getInterface() {
        return IHomeView.class;
    }
}
