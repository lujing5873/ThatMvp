package pers.nchz.thatmvpdemo.home.views.imp;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.Nullable;

import pers.nchz.thatmvp.presenter.ThatBasePresenter;
import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.home.views.IHeadView;

public class HomeHeadView extends ThatBaseView<ThatBasePresenter> implements IHeadView {

    TextView tvTitle;

    @Override
    public void initView(Bundle savedInstanceState) {
        tvTitle=getView(R.id.tv_head);
        System.out.println("head initView ");
        setTitle("robot|robot|robot");
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("点击事件");
            }
        }, R.id.iv_scan);
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("tvTitle");
            }
        });
        getView(R.id.view_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("view_bg");
            }
        });
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.view_home_head;
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
