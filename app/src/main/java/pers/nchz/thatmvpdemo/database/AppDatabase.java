package pers.nchz.thatmvpdemo.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import pers.nchz.thatmvpdemo.home.model.HomeDao;
import pers.nchz.thatmvpdemo.home.model.HomeData;

@Database(
        entities = HomeData.class,
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HomeDao homeDao();
}
