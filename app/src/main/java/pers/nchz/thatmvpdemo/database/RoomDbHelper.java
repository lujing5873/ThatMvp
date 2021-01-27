package pers.nchz.thatmvpdemo.database;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import pers.nchz.thatmvpdemo.MyApp;
import pers.nchz.thatmvpdemo.home.model.HomeDao;
import pers.nchz.thatmvpdemo.home.model.HomeData;

public class  RoomDbHelper {
    AppDatabase db;

    private static final String PATH_HOME_DATA= "preload_home.json";

    private RoomDbHelper() {
    }
    private static final class LazyHolder{
        private static final RoomDbHelper INSTANCE=new RoomDbHelper();
    }
    public static final RoomDbHelper getInstance(){
        return LazyHolder.INSTANCE;
    }
    public void init(){
       db = Room.databaseBuilder(MyApp.getContent(),
               AppDatabase.class, "thatMvp").build();
        Gson gson=new Gson();
        try {
            HomeData[] list = gson.fromJson(
            new InputStreamReader(MyApp.getContent().getAssets().open(PATH_HOME_DATA))
                    ,new TypeToken<HomeData[]>(){}.getType());
            System.out.println("list.length:"+list.length);
            getHomeDao().insertAll(list)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action() {
                        @Override
                        public void run() throws Throwable {
                            System.out.println("插入成功");
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            System.out.println("插入出错");
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   public HomeDao getHomeDao(){
        return db.homeDao();
   }

}
