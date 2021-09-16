package com.nhcz500.base.base.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;

import com.nhcz500.base.R;
import com.nhcz500.base.base.widget.LoadingDialog;
import com.nhcz500.base.network.throwable.HttpThrowable;

import pers.nchz.thatmvp.presenter.KPresenter;
import pers.nchz.thatmvp.kotlin.view.ThatView;

public abstract class BaseViewNormal<P extends KPresenter>
        extends ThatView<P> implements IBaseView, View.OnClickListener{

    ContentLoadingProgressBar progressBar;
    View loading;
    LoadingDialog loadingDialog;
    protected boolean isLoading;
    @Override
    public int getRootLayoutId() {
        return R.layout.view_base;
    }

    public abstract int getContentId();

    @Override
    public void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, Fragment fragment) {
        super.onCreate(inflater, container, savedInstanceState, fragment);
        LayoutInflater.from(getMActivity()).inflate(getContentId(),getRootViewGroup(),true);
        progressBar=getView(R.id.loading_pro);
        loading=getView(R.id.ll_loading);
    }



    @Override
    public void dataFailed(HttpThrowable throwable) {
        Toast.makeText(getMActivity(),throwable.message,Toast.LENGTH_SHORT).show();
        failed(throwable);
    }
    protected  void failed(HttpThrowable throwable){

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void requestData() {

    }

    @Override
    public void showLoading() {
        isLoading=true;
        progressBar.show();
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
        isLoading=false;
        progressBar.hide();
        loading.setVisibility(View.GONE);
    }

    protected void showLoadingDialog(){
        if(loadingDialog==null){
            loadingDialog=new LoadingDialog();
        }
        loadingDialog.show(getMActivity().getSupportFragmentManager(),"loading");
    }
    protected void dismissLoadingDialog(){
        if(loadingDialog!=null){
            loadingDialog.dismiss();
        }
    }

    @Override
    public void onDetach() {
        dismissLoadingDialog();
        super.onDetach();
    }
}
