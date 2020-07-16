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

public abstract class ThatBaseDelegate<V extends ThatBaseView>
        implements IThatBaseDelegate {

    protected V view;

    public void init(){
        try {
            view=getViewClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view.onCreate(inflater,container,savedInstanceState);
    }

    @Override
    public void onDestroy() {
        view.unBindPresenter();
        view=null;
    }

    protected abstract void initData();

    protected abstract Class<V> getViewClass();

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }
}
