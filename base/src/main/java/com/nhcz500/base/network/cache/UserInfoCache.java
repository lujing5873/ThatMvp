package com.nhcz500.base.network.cache;

import com.nhcz500.base.network.NetConstants;
import com.nhcz500.base.network.model.UserInfo;

public class UserInfoCache {
    public static final int USER_TYPE_USER=1;
    public static final int USER_TYPE_RECRUIT=2;
    private UserInfoCache() {
        userInfo=new UserInfo();
//        setToken(NetConstants.AUTH);
    }
    private static final class LazyHolder{
        private static UserInfoCache INSTANCE=new UserInfoCache();
    }
    public static UserInfoCache getInstance(){
        return LazyHolder.INSTANCE;
    }


    private int userType=1;
    private UserInfo userInfo;
    private String token;
    public void setToken(String token){
        this.token=token;
    }
    public String getToken(){
        return token==null?"":token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(boolean isUser) {
        this.userType = isUser?USER_TYPE_USER:USER_TYPE_RECRUIT;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
