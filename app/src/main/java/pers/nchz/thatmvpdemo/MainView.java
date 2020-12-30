package pers.nchz.thatmvpdemo;

import android.os.Bundle;

import pers.nchz.thatmvp.presenter.ThatBasePresenter;
import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;

public class MainView extends ThatBaseView<MainPresenter> implements IMainView {
    @Override
    public void initView(Bundle savedInstanceState) {
        addSubView(new HeadView(),savedInstanceState,0);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Class<? extends IThatBaseView> getInterface() {
        return IMainView.class;
    }

    @Override
    public void mainTest() {

    }
}
