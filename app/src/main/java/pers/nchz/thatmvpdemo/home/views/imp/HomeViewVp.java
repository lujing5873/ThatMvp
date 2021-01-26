package pers.nchz.thatmvpdemo.home.views.imp;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.RoomDbHelper;
import pers.nchz.thatmvpdemo.home.adpters.HomeAdapter;
import pers.nchz.thatmvpdemo.home.adpters.HomeVPAdapter;
import pers.nchz.thatmvpdemo.home.model.HomeData;
import pers.nchz.thatmvpdemo.home.presenters.MainPresenter;
import pers.nchz.thatmvpdemo.home.views.IHomeView;
import pers.nchz.thatmvpdemo.home.views.IMainView;

public class HomeViewVp extends ThatBaseView<MainPresenter> implements IHomeView {
    ViewPager2  vp;
    @Override
    public void initView(Bundle savedInstanceState) {
        vp=getView(R.id.vp_home);
        vp.setAdapter(new HomeVPAdapter());

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
