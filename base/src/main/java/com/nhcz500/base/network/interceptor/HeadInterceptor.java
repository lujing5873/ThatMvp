package com.nhcz500.base.network.interceptor;

import android.text.TextUtils;

import com.nhcz500.base.network.NetConstants;
import com.nhcz500.base.network.cache.UserInfoCache;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder=request.newBuilder();

        String url=request.url().toString();
        if(!url.endsWith("devqiwuzhi.json")&&
                !url.endsWith("demandApp/oauth/token")&&
                !url.endsWith("sys/getWxUserInfo")&&
                !url.endsWith("api-demand-app/sys/homeData")&&
                !TextUtils.isEmpty(UserInfoCache.getInstance().getToken())
        ){
            requestBuilder.addHeader(NetConstants.AUTH_NAME, UserInfoCache.getInstance().getToken());
        }
        request=requestBuilder.build();
        Response response =  chain.proceed(request);

        return response;
    }
}
