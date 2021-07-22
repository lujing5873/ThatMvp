package pers.nchz.thatmvp.java.delegate;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import pers.nchz.thatmvp.R;
import pers.nchz.thatmvp.java.view.IThatBaseView;
import pers.nchz.thatmvp.java.view.ThatBaseView;


/**
 * ThatBaseFragment 是fragment的基类 继承自androidx下的fragment
 * 需要使用getSupportFragmentManager才添加删除
 */

public abstract class ThatBaseFragment extends Fragment   {
    protected View rootView;
    protected IThatBaseView view;
    protected ThatBaseActivity mActivity;
    private boolean isLoad;
    private Bundle savedInstanceState;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity= (ThatBaseActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println(getClass().getName()+">>>>>>>>onCreateView");
//            if((presenter==null)&&mActivity.getPresenterClass() == getPresenterClass()){
//                this.presenter= (P) mActivity.getPresenter();
//            }
        this.savedInstanceState=getArguments();

            try {//分开写为了防止 fragment通过外部注入presenter或者view
                if(view==null){
                    view=getViewClass().newInstance();
                }
            } catch (Exception e) {
                rootView=inflater.inflate(R.layout.fragment_error,container,false);
                return  rootView;
            }
        if(savedInstanceState==null){
            this.savedInstanceState=onViewCreate(getArguments());
        }else{
            Bundle args=getArguments();
            if(args!=null){
                savedInstanceState.putAll(getArguments());
            }
            this.savedInstanceState=onViewCreate(savedInstanceState);
        }
        if(rootView==null){
            view.onCreate(inflater,container,this.savedInstanceState,this);
            if(view.getInterface()!=null){
                mActivity.addView(view);//activity presenter添加
            }
            rootView=view.getRootView();
            if(view!=null){
                view.initView(this.savedInstanceState);
            }
        }else{
            if(view!=null){
                if(view.isNeedClean()){
                    view.onCreate(inflater,container,this.savedInstanceState,this);
                    rootView=view.getRootView();
                    view.initView(this.savedInstanceState);
                }
            }
//            if(view.isNeedRefresh()){
//                view.initView(this.savedInstanceState);
//            }
            view.setViewClean(false);
        }

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        if(mActivity.presenter.getLastView()!=view){
            mActivity.addView(view);
        }
        super.onResume();
        if(!isLoad||view.isNeedRefresh()&&!isHidden()){
            if(view!=null){
                view.lazyData(savedInstanceState);
            }
            isLoad = true;
            view.setViewRefresh(false);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroy() {
        isLoad=false;
        if(view!=null){
            //移除presenter对view的引用
            view.onDetach();
        }
        if(mActivity!=null){
            mActivity.removeView(view);//activity presenter添加
            mActivity=null;
        }
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(rootView!=null){
            ViewGroup parent= (ViewGroup) rootView.getParent();
            if(parent!=null){
                parent.removeView(rootView);
            }
        }
    }

    /**
     * 需要对Bundle进行操作 重写此方法
     * @param savedInstanceState bundle
     * @return
     */
    protected  Bundle onViewCreate(Bundle savedInstanceState) {
        return savedInstanceState;
    }

    /**
     * view的实际class
     * @return view class
     */
    protected abstract Class<? extends ThatBaseView> getViewClass();


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(view!=null){
            view.onConfigurationChanged(newConfig);
        }
    }

    public void setLoad(boolean load) {
        isLoad = load;
    }
}
