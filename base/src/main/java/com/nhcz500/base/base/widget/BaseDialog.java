package com.nhcz500.base.base.widget;

import pers.nchz.thatmvp.java.delegate.ThatActivity;
import pers.nchz.thatmvp.java.view.IThatView;


public abstract class BaseDialog extends FreeCusDialog {
    /**
     * 从activity的presenter中获取别的View
     * @param viewClass
     * @param <T>
     * @return
     */
    protected  <T extends IThatView> T activityView(Class<T> viewClass) {
        return getActivity()==null?null:(T) ((ThatActivity)getActivity()).activityView(viewClass);
    }
}
