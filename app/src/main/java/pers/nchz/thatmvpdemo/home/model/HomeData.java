package pers.nchz.thatmvpdemo.home.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class HomeData {
    @PrimaryKey(autoGenerate = true)
    private Long uid;
    @ColumnInfo(name = "icon")
    private String icon;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "userReal")
    private int userReal;
    @ColumnInfo(name = "watchCount")
    private int watchCount;
    @ColumnInfo(name = "type")
    private int type;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserReal() {
        return userReal;
    }

    public void setUserReal(int userReal) {
        this.userReal = userReal;
    }

    public int getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(int watchCount) {
        this.watchCount = watchCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
