package pers.nchz.thatmvp.java.presenter;

import androidx.lifecycle.Lifecycle;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pers.nchz.thatmvp.java.view.IThatBaseView;

/**
 * basePresenter类
 */

public  class ThatBasePresenter  {
    protected LinkedHashMap<Class<? extends IThatBaseView>,IThatBaseView> mViews=new LinkedHashMap<>();
    protected Lifecycle lifecycle;

    public <T extends IThatBaseView> void addView(T view){
        mViews.put(view.getInterface(),view);
    }
    public <T extends IThatBaseView> void removeView(T view){
        if(mViews.get(view.getInterface())==view){
            mViews.remove(view.getInterface());
        }
    }
    public <T extends IThatBaseView> T getView(Class<T> viewClass) {
        return (T) mViews.get(viewClass);
    }
    public void cleanView(){
        mViews.clear();
        lifecycle=null;
    }

    public void setLifecycle(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    public IThatBaseView getLastView(){
       Object[] cache= mViews.values().toArray();
       if(cache.length==0){
           return null;
       }
       return (IThatBaseView) cache[cache.length-1];
    }

    public int getViewSize(){
        return mViews.size();
    }


    public IThatBaseView getLastBackView(){
        Object[] cache=  mViews.values().toArray();
        if(cache.length==0){
            return null;
        }
        int position=-1;
        for(int i=cache.length-1;i>=0;i--){
            IThatBaseView view= (IThatBaseView) cache[i];
            if(view.onBackPressed()){
                position=i;
                break;
            }
        }
        return  position==-1?null: (IThatBaseView) cache[position];
    }

    public ArrayList<IThatBaseView> getViews(){
        return new ArrayList<>(mViews.values());
    }
}
