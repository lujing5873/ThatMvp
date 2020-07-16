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
    /**
     *  初始化View
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    void initView();
    void initData();
    int getOptionsMenuId();
    int getRootLayoutId();
    View getRootView();
    void setPresenter(ThatBasePresenter presenter);
}
