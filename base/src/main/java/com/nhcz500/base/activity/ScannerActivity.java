package com.nhcz500.base.activity;

import com.nhcz500.base.camerax.presenter.ScannerPresenter;
import com.nhcz500.base.camerax.view.ScannerView;

import pers.nchz.thatmvp.delegate.ThatBaseActivity;

public class ScannerActivity extends ThatBaseActivity {

    @Override
    protected Class getViewClass() {
        return ScannerView.class;
    }

    @Override
    protected Class getPresenterClass() {
        return ScannerPresenter.class;
    }
}
