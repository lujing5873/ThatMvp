package pers.nchz.thatmvpdemo.home.fragments;

import pers.nchz.thatmvp.delegate.ThatBaseFragment;
import pers.nchz.thatmvpdemo.home.presenters.MainPresenter;
import pers.nchz.thatmvpdemo.home.views.imp.HomeView;
import pers.nchz.thatmvpdemo.home.views.imp.HomeViewVp;

public class HomeFrag extends ThatBaseFragment<HomeView, MainPresenter> {
    @Override
    protected Class<HomeView> getViewClass() {
        return HomeView.class;
    }

    @Override
    protected Class<MainPresenter> getPresenterClass() {
        return MainPresenter.class;
    }
}
