package pers.nchz.thatmvp.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import pers.nchz.thatmvp.presenter.ThatBasePresenter;

/**
 *
 */

public interface IThatBaseDelegate {
    void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    void onDestroy();
}
