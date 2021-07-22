package pers.nchz.thatmvp.java.delegate;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pers.nchz.thatmvp.R;
import pers.nchz.thatmvp.java.presenter.ThatBasePresenter;
import pers.nchz.thatmvp.java.view.IThatBaseView;
import pers.nchz.thatmvp.java.view.ThatBaseView;

/**
 * ThatBaseActivity 是activity的基类 继承自android x下的AppCompatActivity
 * 为mvvm扩展做好准备
 */

public abstract class ThatBaseActivity extends AppCompatActivity   {
    /**
     * 保存的根view
     */
    protected View rootView;
    /**
     *实际的view对象
     */
    protected IThatBaseView view;
    /**
     * 实际的presenter对象
     */
    protected ThatBasePresenter presenter;
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
                System.out.println(e);
                setContentView(R.layout.activity_error);
                return;
            }
        }
        //设置presenter
        if(getIntent()!=null&&getIntent().getExtras()!=null){
            savedInstanceState=getIntent().getExtras();
        }
        if(rootView==null){  //如果根View还没有创建
            view.onCreate(getLayoutInflater(),null,savedInstanceState,null); //view 初始化
            rootView=view.getRootView(); //赋值
        }
        if(view.getInterface()!=null){
            presenter.addView(view); //否则把view设置到presenter里
        }
        setContentView(rootView);
        if(view!=null){
            view.initView(savedInstanceState);
        }
    }


    @Override
    protected void onDestroy() {
        System.out.println("onDestroy activity");
        if(presenter!=null){
            //移除presenter对view的引用
            presenter.cleanView();
            presenter=null;
        }
        if(view!=null){
            view.onDetach();
        }
        super.onDestroy();
    }

    /**
     * view的实际class
     * @return view class
     */
    protected abstract Class<? extends ThatBaseView> getViewClass();

    /**
     * presenter的实际类
     * @return presenter class
     */
    protected abstract Class<? extends ThatBasePresenter> getPresenterClass();

    public void removeView(IThatBaseView view){
        if(presenter!=null){
            System.out.println("removeView"+view);
            presenter.removeView(view);
        }

    }

    public void addView(IThatBaseView view){
        if(presenter!=null) {
            presenter.addView(view);
        }
    }

    public <T extends IThatBaseView> T activityView(Class<T> tClass){
       return presenter!=null?presenter.getView(tClass):null;
    }

    public int getViewSize(){
        return presenter==null?0:presenter.getViewSize();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(view!=null){
            view.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if(presenter!=null&&presenter.getLastBackView()!=null){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        if(presenter!=null&&presenter.getLastView()!=null){
            presenter.getLastView().onActivityResult(requestCode, resultCode, data);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void allFresh(){
        for(IThatBaseView sub:presenter.getViews()){
            if(sub !=view){
                sub.setViewRefresh(true);
            }
        }
    }
}
