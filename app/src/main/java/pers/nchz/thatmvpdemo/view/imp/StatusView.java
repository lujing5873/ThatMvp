package pers.nchz.thatmvpdemo.view.imp;

import android.os.Bundle;

import com.nhcz500.base.weiget.StatusBarView;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;
import pers.nchz.thatmvp.presenter.ThatBasePresenter;
import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.view.IStatusView;

public class StatusView extends ThatBaseView<ThatBasePresenter> implements IStatusView {
    @Override
    public void initView(Bundle savedInstanceState) {
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.view_status;
    }

    @Override
    public Class<? extends IThatBaseView> getInterface() {
        return null;
    }

    @Override
    public void setColor(int color) {

    }
}
