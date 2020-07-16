package pers.nchz.thatmvp.presenter;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import pers.nchz.thatmvp.view.IThatBaseView;

/**
 * Created by dell on 2018/3/8.
 */

public abstract class ThatBasePresenter<T extends IThatBaseView  > {

    T view;
    public void setView(T view) {
        this.view = view;
    }
}
