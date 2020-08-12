package pers.nchz.thatmvp.view;


import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import pers.nchz.thatmvp.presenter.ThatBasePresenter;

/**
 * Created by dell on 2017/12/12.
 */

public abstract class ThatBaseSubView<T extends ThatBasePresenter> implements IThatBaseSubView {

    protected Context context;
    protected AppCompatActivity mActivity;
    protected T presenter;
    protected View rootView;
    protected ViewGroup rootViewGroup;
    protected final SparseArray<View> mViews = new SparseArray<View>();
    protected Class<? extends IThatBaseView> tClass;
    public ThatBaseSubView(){
        tClass= (Class<? extends IThatBaseView>) this.getClass().getInterfaces()[0];
       //或者把当前class传递进去,然后presenter getView 方法需要两个参数 一个是接口参数 一个为实际的view实现类
       // presenter.addView(this.getClass(),this);

    }
    @Override
    public void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int rootLayoutId = getRootLayoutId();
        if(rootLayoutId!=0){
            rootView = inflater.inflate(rootLayoutId, container, false);
            rootViewGroup= (ViewGroup) rootView;
            context=rootView.getContext();
            mActivity= (AppCompatActivity) context;
            initView(savedInstanceState);
        }
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    private <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    public <T extends View> T getView(int id) {
        return bindView(id);

    }
    @Override
    public void onDestroy() {
        presenter.removeView(tClass);
    }

    public void setOnClickListener(View.OnClickListener listener, int...ids){
        if(ids==null){
            return;
        }
        for(int id:ids){
            getView(id).setOnClickListener(listener);
        }
    }

    public void addSubView(IThatBaseSubView view,Bundle savedInstanceState){
        view.onCreate(LayoutInflater.from(context),rootViewGroup,savedInstanceState);

        if(view.getRootView()!=null){
            rootViewGroup.addView(view.getRootView());
            view.setPresenter(presenter);
        }
    }

    public void addSubView(IThatBaseSubView view,Bundle savedInstanceState,int index){
        view.onCreate(LayoutInflater.from(context),rootViewGroup,savedInstanceState);

        if(view.getRootView()!=null){
            rootViewGroup.addView(view.getRootView(),index);
            view.setPresenter(presenter);
        }
    }

    @Override
    public <P extends ThatBasePresenter> void setPresenter(P presenter) {
        this.presenter= (T) presenter;
        presenter.addView(tClass,this);
    }
}

