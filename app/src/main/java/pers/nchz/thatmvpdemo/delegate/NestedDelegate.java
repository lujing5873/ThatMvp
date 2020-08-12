package pers.nchz.thatmvpdemo.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import pers.nchz.thatmvp.delegate.ThatBaseDelegate;
import pers.nchz.thatmvpdemo.view.viewImpl.MainView;

/**
 * Created by dell on 2018/3/8.
 */

public class NestedDelegate extends ThatBaseDelegate<MainView>   {

    @Override
    protected Class<MainView> getViewClass() {
        return MainView.class;
    }

}
