package pers.nchz.thatmvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pers.nchz.thatmvp.presenter.ThatBasePresenter;

/**
 *
 */

public interface IThatBaseView {
    void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    void initView(Bundle savedInstanceState);
    int  getRootLayoutId();
    View getRootView();
    <PR extends ThatBasePresenter>void setPresenter(PR presenter);
    Class<?extends IThatBaseView> getInterface();
}
