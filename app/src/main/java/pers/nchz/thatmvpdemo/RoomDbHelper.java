package pers.nchz.thatmvpdemo;

import androidx.room.Room;

import pers.nchz.thatmvpdemo.home.model.HomeDao;

public class  RoomDbHelper {
    AppDatabase db;
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
   }
   public HomeDao getHomeDao(){
        return db.homeDao();
   }

}
