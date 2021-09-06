package com.nhcz500.base.base.widget;

import android.os.Bundle;

import androidx.core.widget.ContentLoadingProgressBar;

import com.nhcz500.base.R;

public class LoadingDialog extends BaseDialog {
    ContentLoadingProgressBar progressBar;
    @Override
    public int getLayoutId() {
        return R.layout.dialog_loading;
    }

    @Override
    protected void createView(Bundle savedInstanceState) {
        setElevation(3);
        setCancel(false);
        progressBar=getView(R.id.loading_pro);
    }
}
