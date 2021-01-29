package pers.nchz.thatmvpdemo.home.views.imp;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.example.freedialog.FreeDialog;

import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.home.adpters.HomeVPAdapter;
import pers.nchz.thatmvpdemo.home.presenters.MainPresenter;
import pers.nchz.thatmvpdemo.home.views.IHeadView;
import pers.nchz.thatmvpdemo.home.views.IHomeView;

public class HomeViewVp extends ThatBaseView<MainPresenter> implements IHomeView {
    ViewPager2  vp;

    @Override
    public void initView(Bundle savedInstanceState) {
//        presenter.getView(IHeadView.class).setTitle("robot | robot | robot");
        vp=getView(R.id.vp_home);
        vp.setAdapter(new HomeVPAdapter(presenter));
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.view_home_vp;
    }

    @Override
    public Class<? extends IThatBaseView> getInterface() {
        return IHomeView.class;
    }
}
