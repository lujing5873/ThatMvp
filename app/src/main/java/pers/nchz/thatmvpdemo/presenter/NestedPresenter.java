package pers.nchz.thatmvpdemo.presenter;

import pers.nchz.thatmvp.presenter.ThatBasePresenter;
import pers.nchz.thatmvpdemo.view.NestedView;

/**
 * Created by dell on 2018/3/8.
 */

public class NestedPresenter extends ThatBasePresenter<NestedView> {


    @Override
    protected Class<NestedView> getViewClass() {
        return NestedView.class;
    }

}
