package pers.nchz.thatmvp.delegate;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import pers.nchz.thatmvp.R;
import pers.nchz.thatmvp.presenter.ThatBasePresenter;
import pers.nchz.thatmvp.view.IThatBaseView;


/**
 * Created by dell on 2017/12/12.
 */

public abstract class ThatBaseFragment<V extends IThatBaseView,P extends ThatBasePresenter>extends Fragment   {
    protected View rootView;
    protected V view;
    protected P presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(view==null||presenter==null){
            try {
                view=getViewClass().newInstance();
                presenter=getPresenterClass().newInstance();
            } catch (Exception e) {
                rootView=inflater.inflate(R.layout.fragment_error,container,false);
                return  rootView;
            }
        }
        if(rootView==null){
            view.onCreate(inflater,container,savedInstanceState);
            presenter.addView(view.getInterface(),view);
            rootView=view.getRootView();
            view.setPresenter(presenter);
        }
        if(view!=null){
            view.initView(savedInstanceState);
        }
        return rootView;
    }
    protected abstract Class<V> getViewClass();
    protected abstract Class<P> getPresenterClass();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter!=null){
            presenter.cleanView();
        }
    }
}
