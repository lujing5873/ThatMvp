package pers.nchz.thatmvpdemo.home.views.imp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

import pers.nchz.thatmvp.presenter.ThatBasePresenter;
import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.home.views.IHeadView;

public class HeadView extends ThatBaseView<ThatBasePresenter> implements IHeadView {

    TextView tvTitle;
    @Override
    public void initView(Bundle savedInstanceState) {
        System.out.println("initView:"+savedInstanceState);
        tvTitle=getView(R.id.tv_head);
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(mActivity).initiateScan();
            }
        }, R.id.iv_scan);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.view_head;
    }

    @Override
    public Class<? extends IThatBaseView> getInterface() {
        return IHeadView.class;
    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }
}