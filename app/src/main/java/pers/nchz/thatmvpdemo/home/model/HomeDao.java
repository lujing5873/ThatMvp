package pers.nchz.thatmvpdemo.home.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface  HomeDao {
    @Query("SELECT * FROM homedata")
    Flowable<List<HomeData>> getAll();


    @Query("SELECT * FROM homedata where type==:type")
    Flowable<List<HomeData>> getAllWithType(int type);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(HomeData... homeData);

    @Update
    void update(HomeData homeData);
    @Delete
    void delete(HomeData homeData);
}
