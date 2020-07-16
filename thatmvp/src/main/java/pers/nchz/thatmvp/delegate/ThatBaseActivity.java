package pers.nchz.thatmvp.delegate;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 *
 */

public abstract class ThatBaseActivity<E extends ThatBaseDelegate> extends AppCompatActivity   {
    protected E baseDelegate;
    public ThatBaseActivity(){
        try {
            if(getDelegateClass()!=null){
                baseDelegate= getDelegateClass().newInstance();
                baseDelegate.init();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(baseDelegate!=null){
            baseDelegate.onCreate(getLayoutInflater(),null,savedInstanceState);
            setContentView(baseDelegate.getView().getRootView());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(baseDelegate!=null){
            baseDelegate.initData();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(baseDelegate!=null){
            baseDelegate.onDestroy();
            baseDelegate=null;
        }
    }

    protected abstract Class<E > getDelegateClass();

}
