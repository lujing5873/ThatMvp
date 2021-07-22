package com.nhcz500.base.utils;

public class NetInfoUtils {
    private NetInfoUtils() {
    }
    private static final class LazyHolder{
        private final static NetInfoUtils INSTANCE=new NetInfoUtils();
    }
    public static NetInfoUtils getInstance(){
        return LazyHolder.INSTANCE;
    }

    private boolean wifiState;
    private boolean netState;

    public boolean isWifiState() {
        return wifiState;
    }

    public void setWifiState(boolean wifiState) {
        this.wifiState = wifiState;
    }

    public boolean isNetState() {
        return netState;
    }

    public void setNetState(boolean netState) {
        this.netState = netState;
    }
}
