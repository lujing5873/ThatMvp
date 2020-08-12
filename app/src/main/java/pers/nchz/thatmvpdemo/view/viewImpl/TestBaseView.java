package pers.nchz.thatmvpdemo.view.viewImpl;

import android.os.Bundle;

import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.presenter.TestPresenter;
import pers.nchz.thatmvpdemo.view.ITestBaseView;

public class TestBaseView extends ThatBaseView<TestPresenter> implements ITestBaseView {
    @Override
    protected Class<TestPresenter> getPresenterClass() {
        return TestPresenter.class;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        if(savedInstanceState==null){
            return;
        }
        int item=savedInstanceState.getInt("item");
        System.out.println(item);
        switch (item){
            case 1:
                addSubView(new TestView1(),savedInstanceState);
                break;
            case 2:
                addSubView(new TestView2(),savedInstanceState);
                break;
            case 3:
                addSubView(new TestView3(),savedInstanceState);
                break;
            case 4:
                addSubView(new TestView4(),savedInstanceState);
                break;
        }
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
        return R.layout.view_base;
    }
}
