package pers.nchz.thatmvpdemo.view.imp;

import android.os.Bundle;
import android.view.View;

import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.presenter.MainPresenter;
import pers.nchz.thatmvpdemo.view.IContentView;
import pers.nchz.thatmvpdemo.view.IHeadView;
import pers.nchz.thatmvpdemo.view.IMainView;

public class ContentView  extends ThatBaseView<MainPresenter> implements IContentView {
    @Override
    public void initView(Bundle savedInstanceState) {
        System.out.println("ContentView:"+presenter.getView(IMainView.class));
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getView(IHeadView.class).setTitle("content fragment title");
            }
        }, R.id.tv_content);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.view_content;
    }

    @Override
    public Class<? extends IThatBaseView> getInterface() {
        return IContentView.class;
    }
}
