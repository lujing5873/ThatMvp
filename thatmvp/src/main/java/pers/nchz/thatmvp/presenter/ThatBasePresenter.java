package pers.nchz.thatmvp.presenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import pers.nchz.thatmvp.view.IThatBaseView;

/**
 * Created by dell on 2018/3/8.
 */

public abstract class ThatBasePresenter<T extends IThatBaseView> {
    protected T view;
    public void setView(){
        try {
            view=getViewClass().newInstance();
            view.setPresenter(this);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public T getView() {
        return view;
    }
    public  void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view.onCreate(inflater,container,savedInstanceState);
    }
    protected abstract Class<T> getViewClass();
}
