package pers.nchz.thatmvp.presenter;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import pers.nchz.thatmvp.view.IThatBaseView;

/**
 * basePresenter类
 */

public abstract class ThatBasePresenter {
    private ArrayMap<Class<? extends IThatBaseView>,IThatBaseView> views;
    public <T extends IThatBaseView> void addView(Class<? extends IThatBaseView> tClass,T view){
        views.put(tClass,view);
    }
    public <T extends IThatBaseView> void removeView(T view){
        views.remove(view);
    }
    public <T extends IThatBaseView> T getView(Class<T> viewClass) {
        return (T) views.get(viewClass);
    }
    public void cleanView(){
        views.clear();
    }
}
