package pers.nchz.thatmvpdemo;

import android.app.Application;

import pers.nchz.thatmvpdemo.database.RoomDbHelper;

public class MyApp  extends Application {
    private static Application CONTENT;
    @Override
    public void onCreate() {
        super.onCreate();
        CONTENT=this;
        RoomDbHelper.getInstance().init();
    }
    public static Application getContent(){
        return CONTENT;
    }
}
