package pers.nchz.thatmvp.delegate;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pers.nchz.thatmvp.R;
import pers.nchz.thatmvp.presenter.ThatBasePresenter;
import pers.nchz.thatmvp.view.IThatBaseView;

/**
 *
 */

public abstract class ThatBaseActivity<V extends IThatBaseView,P extends ThatBasePresenter> extends AppCompatActivity   {
    protected View rootView;
    protected V view;
    protected P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(view==null||presenter==null){
            try {
                view=getViewClass().newInstance();
                presenter=getPresenterClass().newInstance();
            } catch (Exception e) {
                rootView=getLayoutInflater().inflate(R.layout.activity_error,null,false);
            }
        }
        if(rootView==null){
            view.onCreate(getLayoutInflater(),null,savedInstanceState);
            presenter.addView(view.getInterface(),view);
            rootView=view.getRootView();
            view.setPresenter(presenter);
        }
        setContentView(rootView);

        onViewCreate(savedInstanceState);
        if(view!=null){
            view.initView(savedInstanceState);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.cleanView();
        }

    }

    protected abstract Class<V> getViewClass();
    protected abstract Class<P> getPresenterClass();
    protected abstract void onViewCreate(Bundle savedInstanceState);
}
