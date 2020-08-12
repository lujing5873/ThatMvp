package pers.nchz.thatmvpdemo.lifedelegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pers.nchz.thatmvp.delegate.ThatBaseFragment;
import pers.nchz.thatmvpdemo.delegate.MainDelegate;

public class Fragment2 extends ThatBaseFragment<MainDelegate> {
    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState==null){
            savedInstanceState=new Bundle();
        }
        savedInstanceState.putInt("item",2);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
