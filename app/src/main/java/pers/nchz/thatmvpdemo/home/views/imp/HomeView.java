package pers.nchz.thatmvpdemo.home.views.imp;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.RoomDbHelper;
import pers.nchz.thatmvpdemo.home.adpters.HomeAdapter;
import pers.nchz.thatmvpdemo.home.model.HomeData;
import pers.nchz.thatmvpdemo.home.presenters.MainPresenter;
import pers.nchz.thatmvpdemo.home.views.IHomeView;
import pers.nchz.thatmvpdemo.home.views.IMainView;

public class HomeView extends ThatBaseView<MainPresenter> implements IHomeView {
    RecyclerView rv;
    @Override
    public void initView(Bundle savedInstanceState) {
        System.out.println("HomeView:"+presenter.getView(IMainView.class));
        rv=getView(R.id.rv_home);
        rv.setLayoutManager(new LinearLayoutManager(context));

        for(int i=0;i<10;i++){
            HomeData data=new HomeData();
            data.setIcon("icon_123");
            data.setName("robot"+i);
            data.setUserReal(i%2);
            data.setWatchCount(i*11);
//            RoomDbHelper.getInstance().getHomeDao().insertAll(data)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Action() {
//                        @Override
//                        public void run() throws Throwable {
//                            System.out.println("开始插入");
//                        }
//                    }, new Consumer<Throwable>() {
//                        @Override
//                        public void accept(Throwable throwable) throws Throwable {
//                            System.out.println("插入成功");
//                        }
//                    });
        }

        RoomDbHelper.getInstance().getHomeDao().getAll()
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
        return R.layout.view_home;
    }

    @Override
    public Class<? extends IThatBaseView> getInterface() {
        return IHomeView.class;
    }
}
