package pers.nchz.thatmvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pers.nchz.thatmvp.delegate.IThatBaseDelegate;
import pers.nchz.thatmvp.presenter.ThatBasePresenter;

/**
 *
 */

public interface IThatBaseView {

    void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    void initView(Bundle savedInstanceState);
    void initData();
    int getOptionsMenuId();
    int getRootLayoutId();
    View getRootView();
    void onDestroy();
}
