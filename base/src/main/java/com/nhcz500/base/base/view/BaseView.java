package com.nhcz500.base.base.view;

import android.view.View;
import android.widget.Toast;

import com.nhcz500.base.BaseApp;
import com.nhcz500.base.base.widget.LoadingDialog;
import com.nhcz500.base.network.cache.UserInfoCache;
import com.nhcz500.base.network.throwable.HttpThrowable;

import pers.nchz.thatmvp.kotlin.delegate.ThatActivity;
import pers.nchz.thatmvp.kotlin.presenter.KPresenter;
import pers.nchz.thatmvp.kotlin.view.IThatView;
import pers.nchz.thatmvp.kotlin.view.ThatView;

public abstract class BaseView<P extends KPresenter>
        extends ThatView<P> implements IBaseView, View.OnClickListener{
    LoadingDialog loadingDialog;
    @Override
    public void dataFailed(HttpThrowable throwable) {
        System.out.println(throwable.errorType);
        if(throwable.errorType==-1){
            UserInfoCache.getInstance().setToken("");
        }
        Toast.makeText(getMActivity(),throwable.message,Toast.LENGTH_SHORT).show();
        failed(throwable);
    }
    protected  void failed(HttpThrowable throwable){
        dismissLoading();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void requestData() {

    }

    @Override
    public void showLoading() {
        if(loadingDialog==null){
            loadingDialog=new LoadingDialog();
        }
        loadingDialog.show(getMActivity().getSupportFragmentManager(),"loading");
    }

    @Override
    public void dismissLoading() {
        if(loadingDialog!=null){
            loadingDialog.dismiss();
        }
    }

    @Override
    public void onDetach() {
        dismissLoading();
        loadingDialog=null;
        super.onDetach();
    }


    protected <T extends IThatView> T otherActivityView(Class<? extends ThatActivity> activityClass, Class<T> viewClass) {
        ThatActivity activity= BaseApp.getApp().getActivity(activityClass);
        if(activity==null){
            return null;
        }
        return activity.activityView(viewClass);
    }
}
