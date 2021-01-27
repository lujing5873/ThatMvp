package pers.nchz.thatmvpdemo.home.views.imp;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.home.adpters.HomeVPAdapter;
import pers.nchz.thatmvpdemo.home.presenters.MainPresenter;
import pers.nchz.thatmvpdemo.home.views.IHeadView;
import pers.nchz.thatmvpdemo.home.views.IHomeView;

public class HomeView extends ThatBaseView<MainPresenter> implements IHomeView {
    public HomeView(Context context, int type) {
        super(context,type);
    }
    @Override
    public void initView(Bundle savedInstanceState) {
        addSubView(new HomeAppView(context,1),savedInstanceState,0,R.id.content);
        addSubView(new HomeViewVp(context,1),savedInstanceState,1,R.id.content);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.view_home;
    }

    @Override
    public Class<? extends IThatBaseView> getInterface() {
        return IHomeView.class;
    }
}
