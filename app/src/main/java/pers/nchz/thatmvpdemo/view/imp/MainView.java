package pers.nchz.thatmvpdemo.view.imp;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.fragment.MainContentFrag;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.presenter.MainPresenter;
import pers.nchz.thatmvpdemo.view.IHeadView;
import pers.nchz.thatmvpdemo.view.IMainView;

public class MainView extends ThatBaseView<MainPresenter> implements IMainView {
    private BottomNavigationView navigationView;
    @Override
    public void initView(Bundle savedInstanceState) {
        navigationView=getView(R.id.nav_main);
        navigationView.setItemIconTintList(null);//关闭着色
        addSubView(new StatusView(),savedInstanceState,0);
        addSubView(new HeadView(),savedInstanceState,1);
        presenter.getView(IHeadView.class).setTitle("酒店服务员 | 临时保姆 | 发传单");
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
