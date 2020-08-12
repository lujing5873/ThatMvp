package pers.nchz.thatmvpdemo.view.viewImpl;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.lifedelegate.Fragment1;
import pers.nchz.thatmvpdemo.lifedelegate.FragmentTest1;
import pers.nchz.thatmvpdemo.presenter.MainPresenter;
import pers.nchz.thatmvpdemo.view.IHeadView;
import pers.nchz.thatmvpdemo.view.IMainView;

/**
 * Created by dell on 2018/3/8.
 */

public class MainView extends ThatBaseView<MainPresenter> implements IMainView {
    private IHeadView headView;
    BottomNavigationView bnv;
    FrameLayout fl_content;
    @Override
    public void initView(Bundle savedInstanceState) {
        bnv=getView(R.id.bnv_main);
        fl_content=getView(R.id.fl_content);
        headView=new HeadView();
        addSubView(headView,savedInstanceState,0);

        mActivity.getSupportFragmentManager().beginTransaction().add(R.id.fl_content,new Fragment1(),"test1").commit();

    }
    @Override
    public void initData() {
        presenter.getData();
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.view_main;
    }


    @Override
    protected Class<MainPresenter> getPresenterClass() {
        return MainPresenter.class;
    }

    @Override
    public void newVoid() {

    }

    @Override
    public void setMainCurrentItem(int position) {

    }


}
