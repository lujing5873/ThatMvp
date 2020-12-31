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
import pers.nchz.thatmvp.view.ThatBaseView;

/**
 * ThatBaseActivity 是activity的基类 继承自android x下的AppCompatActivity
 * 为mvvm扩展做好准备
 */

public abstract class ThatBaseActivity<V extends ThatBaseView<P>,P extends ThatBasePresenter> extends AppCompatActivity   {
    /**
     * 保存的根view
     */
    protected View rootView;
    /**
     *实际的view对象
     */
    protected V view;
    /**
     * 实际的presenter对象
     */
    protected P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实例化view和presenter
        if(view==null||presenter==null){
            try {
                view=getViewClass().newInstance();
                presenter=getPresenterClass().newInstance();
            } catch (Exception e) {
                //出错加载错误页面
                setContentView(R.layout.activity_error);
                return;
            }
        }
        if(rootView==null){  //如果根View还没有创建
            view.onCreate(getLayoutInflater(),null,savedInstanceState); //view 初始化
            presenter.addView(view.getInterface(),view); //presenter 添加
            rootView=view.getRootView(); //赋值
            view.setPresenter(presenter); //设置presenter
        }else{
            presenter.addView(view.getInterface(),view); //否则把view设置到presenter里
        }
        setContentView(rootView);
        savedInstanceState=onViewCreate(savedInstanceState);
        if(view!=null){
            view.initView(savedInstanceState);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
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
    protected  Bundle onViewCreate(Bundle savedInstanceState) {
        return savedInstanceState;
    }
}
