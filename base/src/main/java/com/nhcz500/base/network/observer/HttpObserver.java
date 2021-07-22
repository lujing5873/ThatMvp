package com.nhcz500.base.network.observer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.nhcz500.base.network.model.BaseModel;
import com.nhcz500.base.network.throwable.HttpThrowable;
import com.nhcz500.base.network.throwable.ThrowableHandler;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public abstract class HttpObserver<T> implements Observer<BaseModel<T>>, LifecycleObserver {

    private Lifecycle lifecycle;
    private Disposable disposable;

    public HttpObserver(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
        if(lifecycle!=null){
            lifecycle.addObserver(this);
        }
    }


    @Override
    public void onComplete() {
        if(disposable!=null){
            disposable.dispose();
            lifecycle.removeObserver(this);
        }
    }

    @Override
    public void onNext(@NonNull BaseModel<T> data) {
        if(data.getCode()==200&&data.getData()!=null){//400token失效
            success(data.getData());
        }else{
            failed(new HttpThrowable(data.getCode(),data.getMsg(),new Throwable()));
        }
    }

    @Override
    public void onError(@NonNull Throwable throwable) {
        if (throwable instanceof Exception) {
            failed(ThrowableHandler.handleThrowable(throwable));
        } else {
            failed(new HttpThrowable(HttpThrowable.UNKNOWN,"未知错误",throwable));
        }
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposable=d;
    }

    protected abstract void success(T t);
    protected abstract void failed(HttpThrowable httpThrowable);

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void destroy(){
       if(disposable!=null){
           disposable.dispose();
           lifecycle.removeObserver(this);
           lifecycle=null;
       }
    }
}
