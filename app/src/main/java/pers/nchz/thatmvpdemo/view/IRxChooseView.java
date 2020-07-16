package pers.nchz.thatmvpdemo.view;

import java.util.List;

import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvpdemo.bean.ImageBean;


/**
 * Created by dell on 2018/1/13.
 */

public interface IRxChooseView extends IThatBaseView {
    void newVoid();
    void getDataSuccess(List<ImageBean> list);
}
