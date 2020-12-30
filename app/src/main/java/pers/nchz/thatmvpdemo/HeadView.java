package pers.nchz.thatmvpdemo;

import android.os.Bundle;

import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;

public class HeadView extends ThatBaseView implements IHeadView {
    @Override
    public void initView(Bundle savedInstanceState) {

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
    public void test() {

    }
}
