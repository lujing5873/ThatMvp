package pers.nchz.thatmvp.delegate;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pers.nchz.thatmvp.presenter.ThatBasePresenter;
import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;

/**
 *
 */

public abstract class ThatBaseDelegate<V extends IThatBaseView>
        implements IThatBaseDelegate {

    protected V view;

    public ThatBaseDelegate(){
        try {
            view=getViewClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view.onCreate(inflater,container,savedInstanceState);
        initView(savedInstanceState);
    }


    protected void initView(Bundle savedInstanceState){
        view.initView(savedInstanceState);
    }
    protected void initData(){
        view.initData();
    }
    protected void onStart(){}
    protected void onPause(){}
    protected void onResume(){}
    protected void onDestroy(){
        view.onDestroy();
    }

    protected abstract Class<V> getViewClass();

    @Override
    public <V extends IThatBaseView> V getView() {
        return (V) view;
    }
}
