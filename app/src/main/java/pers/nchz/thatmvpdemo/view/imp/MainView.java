package pers.nchz.thatmvpdemo.view.imp;

import android.os.Bundle;

import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.fragment.MainContentFrag;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.presenter.MainPresenter;
import pers.nchz.thatmvpdemo.view.IHeadView;
import pers.nchz.thatmvpdemo.view.IMainView;

public class MainView extends ThatBaseView<MainPresenter> implements IMainView {

    @Override
    public void initView(Bundle savedInstanceState) {
        addSubView(new HeadView(),savedInstanceState,0);
        presenter.getView(IHeadView.class).setTitle("MainView set Title");
        mActivity.getSupportFragmentManager().beginTransaction().add(R.id.fl_content,new MainContentFrag(presenter)).commit();
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
