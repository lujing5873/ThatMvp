package pers.nchz.thatmvp.delegate;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


/**
 * Created by dell on 2017/12/12.
 */

public abstract class ThatBaseFragment<E extends ThatBaseDelegate>extends Fragment   {
    protected View rootView;
    protected E baseDelegate;
    public ThatBaseFragment(){
        try {
            baseDelegate= getDelegateClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView==null){
            baseDelegate.onCreate(inflater,container,savedInstanceState);
            rootView=baseDelegate.getView().getRootView();
        }
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(baseDelegate!=null){
            baseDelegate.initData();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if(baseDelegate!=null){
            baseDelegate.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(baseDelegate!=null){
            baseDelegate.onPause();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(baseDelegate!=null){
            baseDelegate.onDestroy();
        }
    }

    protected abstract Class<E > getDelegateClass();

}
