package pers.nchz.thatmvpdemo.fragment;

import pers.nchz.thatmvp.delegate.ThatBaseFragment;
import pers.nchz.thatmvpdemo.presenter.MainPresenter;
import pers.nchz.thatmvpdemo.view.imp.ContentView;

public class MainContentFrag extends ThatBaseFragment<ContentView, MainPresenter> {
    public MainContentFrag(MainPresenter presenter){
        this.presenter=presenter;
    }
    @Override
    protected Class<ContentView> getViewClass() {
        return ContentView.class;
    }

    @Override
    protected Class<MainPresenter> getPresenterClass() {
        return MainPresenter.class;
    }
}
