package pers.nchz.thatmvpdemo;

import android.app.Application;

public class MyApp  extends Application {
    private static Application CONTENT;
    @Override
    public void onCreate() {
        super.onCreate();
        CONTENT=this;
        RoomDbHelper.getInstance().init();
    }
    static Application getContent(){
        return CONTENT;
    }
}
