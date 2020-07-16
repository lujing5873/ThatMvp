package pers.nchz.thatmvp.view;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pers.nchz.thatmvp.delegate.IThatBaseDelegate;
import pers.nchz.thatmvp.presenter.ThatBasePresenter;

/**
 * Created by dell on 2017/12/12.
 */

public abstract class ThatBaseView<T extends ThatBasePresenter> implements IThatBaseView {

    protected Context context;
    protected T presenter;
    protected View rootView;
    protected final SparseArray<View> mViews = new SparseArray<View>();

    public ThatBaseView(){
        try {
            presenter=getPresenterClass().newInstance();
            presenter.setView(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int rootLayoutId = getRootLayoutId();
        rootView = inflater.inflate(rootLayoutId, container, false);
        context=rootView.getContext();
        initView();
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    private <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    public <T extends View> T getView(int id) {
        return bindView(id);

    }
    protected abstract Class<T> getPresenterClass();
    @Override
    public void setPresenter(ThatBasePresenter presenter) {
        try {
            this.presenter= (T) presenter;
        }catch (Exception ex){
            Log.i("presenter强转失败：类名",getClass().getName());
        }
    }
    public void unBindPresenter(){
        presenter.setView(null);
    }

}

