package pers.nchz.thatmvpdemo;

import android.content.res.Configuration;
import android.os.Bundle;

import java.util.ServiceLoader;

import pers.nchz.thatmvp.delegate.ThatBaseActivity;
import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvpdemo.home.presenters.MainPresenter;
import pers.nchz.thatmvpdemo.home.views.imp.MainView;

public class MainActivity extends ThatBaseActivity {


    @Override
    protected Class<MainView> getViewClass() {
        return MainView.class;
    }

    @Override
    protected Class<MainPresenter> getPresenterClass() {
        return MainPresenter.class;
    }

    @Override
    protected Bundle onViewCreate(Bundle savedInstanceState) {
//        ServiceLoader<IThatBaseView> serviceLoader=ServiceLoader.load(IThatBaseView.class);
        if(savedInstanceState==null){
            savedInstanceState=new Bundle();
        }
        savedInstanceState.putString("test","test");
        return super.onViewCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop");
    }
}
