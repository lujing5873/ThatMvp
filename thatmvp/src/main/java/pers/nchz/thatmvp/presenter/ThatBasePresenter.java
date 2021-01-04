package pers.nchz.thatmvp.presenter;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;

/**
 * basePresenter类
 */

public abstract class ThatBasePresenter  {
    protected ArrayMap<Class<? extends IThatBaseView>,IThatBaseView> views=new ArrayMap<>();
    public <T extends IThatBaseView> void addView(Class<? extends IThatBaseView> tClass,T view){
        views.put(tClass,view);
    }
    public <T extends IThatBaseView> void removeView(Class<T> view){
        views.remove(view);
    }
    public <T extends IThatBaseView> T getView(Class<T> viewClass) {
        return (T) views.get(viewClass);
    }
    public void cleanView(){
        views.clear();
    }
}
