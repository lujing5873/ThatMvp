package pers.nchz.thatmvpdemo.home.views.imp;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.database.RoomDbHelper;
import pers.nchz.thatmvpdemo.home.adpters.HomeAdapter;
import pers.nchz.thatmvpdemo.home.model.HomeData;
import pers.nchz.thatmvpdemo.home.presenters.MainPresenter;
import pers.nchz.thatmvpdemo.home.views.IHomeView;
import pers.nchz.thatmvpdemo.home.views.IMainView;

public class HomeContentView extends ThatBaseView<MainPresenter> implements IHomeView {
    public static final String TYPE="type";
    RecyclerView rv;

    public HomeContentView(Context context) {
        super(context);
    }

    public HomeContentView(Context context, int type) {
        super(context, type);
    }

    public HomeContentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        IMainView mainView=presenter.getView(IMainView.class);
        System.out.println(mainView);
        rv=getView(R.id.rv_home);
        rv.setLayoutManager(new LinearLayoutManager(context));
        int type=1;
        if(savedInstanceState!=null){
            type=savedInstanceState.getInt(TYPE);
        }
        RoomDbHelper.getInstance().getHomeDao().getAllWithType(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<HomeData>>() {
            @Override
            public void accept(List<HomeData> homeData) throws Throwable {
                System.out.println("查询成功");
                System.out.println(homeData.size());
                rv.setAdapter(new HomeAdapter(homeData));
            }
        });

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.view_home_content;
    }

    @Override
    public Class<? extends IThatBaseView> getInterface() {
        return IHomeView.class;
    }
}
