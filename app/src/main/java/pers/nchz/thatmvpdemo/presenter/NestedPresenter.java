package pers.nchz.thatmvpdemo.presenter;

import android.os.Handler;

import pers.nchz.thatmvp.presenter.ThatBasePresenter;
import pers.nchz.thatmvpdemo.view.INestedView;
import pers.nchz.thatmvpdemo.view.NestedView;

/**
 * Created by dell on 2018/3/8.
 */

public class NestedPresenter extends ThatBasePresenter {

    public void getData(){
       Handler handler=new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               getView(INestedView.class).newVoid();
           }
       },500);

    }
}
