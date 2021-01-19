package pers.nchz.thatmvpdemo.home.views.imp;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.home.presenters.MainPresenter;
import pers.nchz.thatmvpdemo.home.views.IHomeView;
import pers.nchz.thatmvpdemo.home.views.IMainView;

public class HomeView extends ThatBaseView<MainPresenter> implements IHomeView {
    RecyclerView rv;
    @Override
    public void initView(Bundle savedInstanceState) {
        System.out.println("HomeView:"+presenter.getView(IMainView.class));
        rv=getView(R.id.rv_home);
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
