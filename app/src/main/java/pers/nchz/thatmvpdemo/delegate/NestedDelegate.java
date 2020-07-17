package pers.nchz.thatmvpdemo.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pers.nchz.thatmvp.delegate.ThatBaseDelegate;
import pers.nchz.thatmvp.presenter.ThatBasePresenter;
import pers.nchz.thatmvpdemo.bean.TestBean;
import pers.nchz.thatmvpdemo.presenter.NestedPresenter;
import pers.nchz.thatmvpdemo.view.NestedView;

/**
 * Created by dell on 2018/3/8.
 */

public class NestedDelegate extends ThatBaseDelegate<NestedView>   {

    @Override
    protected Class<NestedView> getViewClass() {
        return NestedView.class;
    }

    @Override
    public void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(inflater, container, savedInstanceState);
        initView(savedInstanceState);
    }
}
