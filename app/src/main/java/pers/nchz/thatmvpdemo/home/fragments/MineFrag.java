package pers.nchz.thatmvpdemo.home.fragments;

import pers.nchz.thatmvp.delegate.ThatBaseFragment;
import pers.nchz.thatmvpdemo.home.presenters.MainPresenter;
import pers.nchz.thatmvpdemo.home.views.imp.MineView;

public class MineFrag extends ThatBaseFragment<MineView, MainPresenter> {
    @Override
    protected Class<MineView> getViewClass() {
        return MineView.class;
    }

    @Override
    protected Class<MainPresenter> getPresenterClass() {
        return MainPresenter.class;
    }
}
