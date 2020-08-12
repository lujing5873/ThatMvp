package pers.nchz.thatmvpdemo.lifedelegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import pers.nchz.thatmvp.delegate.ThatBaseFragment;
import pers.nchz.thatmvpdemo.delegate.MainDelegate;

public class Fragment1 extends ThatBaseFragment<MainDelegate> {
    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("onCreateView");
        if(savedInstanceState==null){
            savedInstanceState=new Bundle();
        }
        savedInstanceState.putInt("item",1);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
