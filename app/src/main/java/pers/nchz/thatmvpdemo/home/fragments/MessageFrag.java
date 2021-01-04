package pers.nchz.thatmvpdemo.home.fragments;

import pers.nchz.thatmvp.delegate.ThatBaseFragment;
import pers.nchz.thatmvpdemo.home.presenters.MainPresenter;
import pers.nchz.thatmvpdemo.home.views.imp.MessageView;

public class MessageFrag extends ThatBaseFragment<MessageView, MainPresenter> {
    @Override
    protected Class<MessageView> getViewClass() {
        return MessageView.class;
    }

    @Override
    protected Class<MainPresenter> getPresenterClass() {
        return MainPresenter.class;
    }
}
