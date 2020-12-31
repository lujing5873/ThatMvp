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
import pers.nchz.thatmvp.view.ThatBaseView;


/**
 * ThatBaseFragment 是fragment的基类 继承自androidx下的fragment
 * 需要使用getSupportFragmentManager才添加删除
 */

public abstract class ThatBaseFragment<V extends ThatBaseView<P>,P extends ThatBasePresenter>extends Fragment   {
    protected View rootView;
    protected V view;
    protected P presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            try {//分开写为了防止 fragment通过外部注入presenter或者view
                if(view==null){
                view=getViewClass().newInstance();
                }
                if(presenter==null){
                    presenter=getPresenterClass().newInstance();
                }
            } catch (Exception e) {
                rootView=inflater.inflate(R.layout.fragment_error,container,false);
                return  rootView;
            }

        if(rootView==null){
            view.onCreate(inflater,container,savedInstanceState);
            presenter.addView(view.getInterface(),view);
            rootView=view.getRootView();
            view.setPresenter(presenter);
        }else{
            presenter.addView(view.getInterface(),view);
        }
        savedInstanceState=onViewCreate(savedInstanceState);
        if(view!=null){
            view.initView(savedInstanceState);
        }
        return rootView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter!=null){
            //移除presenter对view的引用
            presenter.removeView(getViewClass());
        }
    }
    /**
     * view的实际class
     * @return view class
     */
    protected abstract Class<V> getViewClass();
    /**
     * presenter的实际类
     * @return presenter class
     */
    protected abstract Class<P> getPresenterClass();


    /**
     * 需要对Bundle进行操作 重写此方法
     * @param savedInstanceState bundle
     * @return
     */
    protected  Bundle onViewCreate(Bundle savedInstanceState){
        return savedInstanceState;
    }
}
