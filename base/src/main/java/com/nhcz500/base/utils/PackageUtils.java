package com.nhcz500.base.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

public class PackageUtils {
    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }



    public static boolean isInstalled(String packageName,Context cxt) {
        final PackageManager packageManager = cxt.getPackageManager();
        List<PackageInfo> packageList = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<>();
        if (packageList != null) {
            for (int i = 0; i < packageList.size(); i++) {
                String pn = packageList.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
    }
}
