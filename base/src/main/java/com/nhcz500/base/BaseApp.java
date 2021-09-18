package com.nhcz500.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;


import java.util.ArrayList;
import java.util.LinkedHashMap;

import pers.nchz.thatmvp.delegate.ThatActivity;


public class BaseApp extends Application {
    public static LinkedHashMap<Class<? extends ThatActivity>, ThatActivity> activitys=new LinkedHashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        LazyHolder.app=this;
        registerActivityLifecycleCallbacks(new SwitchBackgroundCallbacks());

    }

    private static final class LazyHolder{
        private static BaseApp app;
    }

    public static Context getContext(){
        if (LazyHolder.app == null) {
            throw new IllegalStateException("Application can not initialized");
        }
        return  LazyHolder.app.getBaseContext();
    }


    public static BaseApp getApp(){
        if (LazyHolder.app == null) {
            throw new IllegalStateException("Application can not initialized");
        }
        return  LazyHolder.app;
    }


    public ThatActivity getActivity(Class<? extends ThatActivity> classOf){
       return activitys.get(classOf);
    }

    public ArrayList<ThatActivity> getAllActivity(){
        return new ArrayList<>(activitys.values());
    }


    private class SwitchBackgroundCallbacks implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if(activity instanceof ThatActivity) {
                activitys.put(((ThatActivity)activity).getClass(), (ThatActivity)activity);
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            if(activity instanceof ThatActivity) {
                activitys.remove(activity.getClass());
            }
        }
    }
}
