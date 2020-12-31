package pers.nchz.thatmvpdemo;

import android.os.Bundle;

import java.util.ServiceLoader;

import pers.nchz.thatmvp.delegate.ThatBaseActivity;
import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvpdemo.presenter.MainPresenter;
import pers.nchz.thatmvpdemo.view.imp.MainView;

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
        ServiceLoader<IThatBaseView> serviceLoader=ServiceLoader.load(IThatBaseView.class);
        System.out.println(serviceLoader);
        for(IThatBaseView service : serviceLoader) {
            System.out.println("interfaces: "+service.getInterface());
        }
        if(savedInstanceState==null){
            savedInstanceState=new Bundle();
        }
        savedInstanceState.putString("test","test");
        return super.onViewCreate(savedInstanceState);
    }
}
