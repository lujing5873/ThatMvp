package pers.nchz.thatmvpdemo.view.viewImpl;

import android.os.Bundle;

import pers.nchz.thatmvp.view.ThatBaseSubView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.presenter.TestPresenter;
import pers.nchz.thatmvpdemo.view.ITestView1;
import pers.nchz.thatmvpdemo.view.ITestView3;

public class TestView3 extends ThatBaseSubView<TestPresenter> implements ITestView3 {


    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.view_test3;
    }
}
