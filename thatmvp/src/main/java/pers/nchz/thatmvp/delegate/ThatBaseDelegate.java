package pers.nchz.thatmvp.delegate;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pers.nchz.thatmvp.presenter.ThatBasePresenter;
import pers.nchz.thatmvp.view.IThatBaseView;

/**
 *
 */

public abstract class ThatBaseDelegate<P extends ThatBasePresenter>
        implements IThatBaseDelegate {

    protected P presenter;

    public void init(){
        try {
            presenter=getPresentClass().newInstance();
            presenter.setView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter.onCreate(inflater,container,savedInstanceState);

    }

    protected abstract void initData();

    protected abstract Class<P> getPresentClass();

    @Override
    public <T extends ThatBasePresenter> T getPresenter() {
        return (T) presenter;
    }
}
