package pers.nchz.thatmvpdemo.presenter;

import android.os.Handler;
import android.widget.ImageView;

import pers.nchz.thatmvp.presenter.ThatBasePresenter;
import pers.nchz.thatmvpdemo.view.IHeadView;
import pers.nchz.thatmvpdemo.view.IMainView;

/**
 * Created by dell on 2018/3/8.
 */

public class MainPresenter extends ThatBasePresenter {

    public void getData(){
       Handler handler=new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               getView(IMainView.class).newVoid();
           }
       },500);
       getView(IHeadView.class).test();

    }
    public void setMainCurrentItem(int position){
        getView(IMainView.class).setMainCurrentItem(position);
    }
}
