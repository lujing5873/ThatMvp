package pers.nchz.thatmvpdemo;

import android.os.Bundle;

import pers.nchz.thatmvp.delegate.ThatBaseActivity;

public class MainActivity extends ThatBaseActivity {
    @Override
    protected Class getViewClass() {
        return MainView.class;
    }

    @Override
    protected Class getPresenterClass() {
        return MainPresenter.class;
    }

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        if(savedInstanceState==null){
            savedInstanceState=new Bundle();
        }
        savedInstanceState.putString("title","ThatMvp Head");
    }
}
