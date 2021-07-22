package com.nhcz500.base.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

public class StartMapUtils {
    public static String BAIDU="com.baidu.BaiduMap";
    public static String GAODE="com.autonavi.minimap";
    public static String TENGXUN="com.tencent.map";
    public static boolean baiduMap(Activity activity, double lat, double lon) {

        if (PackageUtils.isInstalled(BAIDU,activity)) {
            Intent intent = new Intent();
            intent.setData(Uri.parse("baidumap://map/geocoder?location=" +lat + "," + lon +
                    "&zoom=11" +
                    "&coord_type=gcj02" +
                    "&src=" + activity.getPackageName()));
            activity.startActivity(intent);
            return true;
        }
        return false;
    }


    /**
     * 启动高德地图，
     */
    public static boolean gaoDeMap(Activity activity, double lat, double lon) {
        if(!PackageUtils.isInstalled(GAODE,activity)){
            return false;
        }
        Intent intent=new Intent();
        intent.setPackage(GAODE);
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("androidamap://viewReGeo?sourceApplication=qiwuzhi&lat="+lat+"&lon="+lon+"&dev=0"));
        activity.startActivity(intent);
        return true;
    }
}
