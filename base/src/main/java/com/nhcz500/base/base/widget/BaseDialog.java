package com.nhcz500.base.base.widget;

import com.nhcz500.base.weiget.FreeCusDialog;

import pers.nchz.thatmvp.java.delegate.ThatBaseActivity;
import pers.nchz.thatmvp.java.view.IThatBaseView;


public abstract class BaseDialog extends FreeCusDialog {
    /**
     * 从activity的presenter中获取别的View
     * @param viewClass
     * @param <T>
     * @return
     */
    protected  <T extends IThatBaseView> T activityView(Class<T> viewClass) {
        return getActivity()==null?null:(T) ((ThatBaseActivity)getActivity()).activityView(viewClass);
    }
}
