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
    protected Class<? extends IThatBaseView> tClass;
    public ThatBaseView(){
        try {
            if(getPresenterClass()!=null){
                presenter=getPresenterClass().newInstance();
                tClass= (Class<? extends IThatBaseView>) this.getClass().getInterfaces()[0];
                presenter.addView(tClass,this);
                //或者把当前class传递进去,然后presenter getView 方法需要两个参数 一个是接口参数 一个为实际的view实现类
                // presenter.addView(this.getClass(),this);
            }
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
    public void onDestroy() {
        presenter.removeView(tClass);
    }

    public void setOnClickListener(View.OnClickListener listener, int...ids){
        if(ids==null){
            return;
        }
        for(int id:ids){
            getView(id).setOnClickListener(listener);
        }
    }

}

