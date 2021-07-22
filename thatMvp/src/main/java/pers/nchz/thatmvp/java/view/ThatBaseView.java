package pers.nchz.thatmvp.java.view;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import pers.nchz.thatmvp.java.delegate.ThatBaseActivity;
import pers.nchz.thatmvp.java.presenter.ThatBasePresenter;

/**
 *
 */

public abstract class ThatBaseView <P extends ThatBasePresenter>  implements IThatBaseView, LifecycleObserver {
    protected Context context;
    protected AppCompatActivity mActivity;
    protected ThatBaseActivity baseActivity;
    protected Fragment fragment;
    protected P presenter;
    protected View rootView;
    protected ViewGroup rootViewGroup;
    protected final SparseArray<View> mViews = new SparseArray<View>();
    protected Lifecycle lifecycle;
    protected  Class<? extends IThatBaseView> classOfView;
    protected boolean isShowDialog;//是否显示dialog

    protected boolean needRefresh;//是否需要刷新数据
    protected boolean needClean;//是否需要清空页面数据

    @Override
    public void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, Fragment fragment) {
        int rootLayoutId = getRootLayoutId();
        if(rootLayoutId>=0){
            if(needClean){
                mViews.clear();
            }
            Class<?> interfaces[] = getClass().getInterfaces();
            for (Class<?> inter : interfaces) {//打印
                if(IThatBaseView.class.isAssignableFrom(inter)){
                    classOfView= (Class<? extends IThatBaseView>) inter;
                    break;
                }
            }
            if(classOfView==null){
                interfaces= getClass().getSuperclass().getInterfaces();
                for (Class<?> inter : interfaces) {//打印
                    if(IThatBaseView.class.isAssignableFrom(inter)){
                        classOfView= (Class<? extends IThatBaseView>) inter;
                        break;
                    }
                }
            }

            rootView = inflater.inflate(rootLayoutId, container, false);
            if(rootView instanceof ViewGroup){
                rootViewGroup= (ViewGroup) rootView;
            }else{
                rootViewGroup=null;
            }
            context=rootView.getContext();
            mActivity= (AppCompatActivity) context;
            if(mActivity instanceof ThatBaseActivity){
                baseActivity= (ThatBaseActivity) mActivity;
            }
            try {
               presenter= (P) getClassType(this).newInstance();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            if(fragment!=null){
                this.fragment=fragment;
                this.lifecycle=fragment.getLifecycle();
            }else{
                this.lifecycle=mActivity.getLifecycle();
            }
            if(presenter!=null){
                presenter.addView(this);
                presenter.setLifecycle(lifecycle);
            }
            lifecycle.addObserver(this);
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
    public void setOnClickListener(View.OnClickListener listener, int...ids){
        if(ids==null){
            return;
        }
        for(int id:ids){
            getView(id).setOnClickListener(listener);
        }
    }

    /**
     * 添加子View并且解放Presenter不同类型
     * @param view
     * @param savedInstanceState
     * @param index
     * @param viewGroup
     */
    private void addView(IThatBaseView view,Bundle savedInstanceState,int index,ViewGroup viewGroup){
        view.onCreate(LayoutInflater.from(context),rootViewGroup,savedInstanceState,fragment);
        if(view.getRootView()!=null){
            viewGroup.addView(view.getRootView(),index);
            presenter.addView(view);
            view.initView(savedInstanceState);
        }
    }

    private Class getClassType(IThatBaseView view){
        Type superClass= view.getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        Class classType;
        if (type instanceof ParameterizedType) {
            classType = (Class) ((ParameterizedType) type).getRawType();
        } else {
            classType = (Class) type;
        }
        return classType;
    }

    public void addSubView(IThatBaseView view,Bundle savedInstanceState){
        if(rootViewGroup==null){
            return;
        }
        addView(view,savedInstanceState,rootViewGroup.getChildCount(),rootViewGroup);
    }

    public void addSubView(IThatBaseView view,Bundle savedInstanceState,int index,int id){
        ViewGroup addViewGroup=rootView.findViewById(id);
        if(addViewGroup==null){
            return;
        }
        addView(view,savedInstanceState,index,addViewGroup);
    }

    public void addSubView(IThatBaseView view,Bundle savedInstanceState,int index) {
        if(rootViewGroup==null){
            return;
        }
       addView(view, savedInstanceState, index,rootViewGroup);
    }


    /**
     * 从activity的presenter中获取别的View
     * @param viewClass
     * @param <T>
     * @return
     */
    protected  <T extends IThatBaseView> T activityView(Class<T> viewClass) {
        return baseActivity==null?null:(T) baseActivity.activityView(viewClass);
    }

    @Override
    public void lazyData(Bundle savedInstanceState) {
        for(IThatBaseView sub:presenter.getViews()){
            if(sub!=this){
                sub.lazyData(savedInstanceState);
            }
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onDetach() {
        if(presenter!=null){
            presenter.cleanView();
        }
        mViews.clear();
        fragment=null;
        mActivity=null;
        context=null;
        baseActivity=null;
        rootView=null;
        rootViewGroup=null;
        lifecycle.removeObserver(this);
        lifecycle=null;
    }

    @Override
    public Class<? extends IThatBaseView> getInterface() {
        return classOfView;
    }
    protected  boolean isDarkStatus(){
        return false;
    }
    protected boolean isNeedStatus(){
        return false;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void setStatusText(){
        if(isAndroidMOrAbove()&&(isDarkStatus()||isNeedStatus())){
            setStatusTextColor(isDarkStatus());
        }
    }


    //Android Api 23以上
    private static boolean isAndroidMOrAbove() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    protected void setStatusTextColor(boolean isDark){
        View decor = mActivity.getWindow().getDecorView();
        if (isDark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    public void popBackView(){
        if(mActivity!=null){
            if(mActivity.getSupportFragmentManager().getBackStackEntryCount()==0){
                mActivity.finish();
            }else{
                mActivity.getSupportFragmentManager().popBackStack();
            }
        }
    }

    protected final String getString(@StringRes int resId) {
        return mActivity.getResources().getString(resId);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void showJustPan(boolean show) {
    }

    @Override
    public boolean isShowDialog() {
        return isShowDialog;
    }

    @Override
    public boolean isNeedClean() {
        return needClean;
    }

    @Override
    public boolean isNeedRefresh() {
        return needRefresh;
    }

    @Override
    public void setViewClean(boolean need) {
        needClean=need;
    }

    @Override
    public void setViewRefresh(boolean need) {
        needRefresh=need;
    }
}

