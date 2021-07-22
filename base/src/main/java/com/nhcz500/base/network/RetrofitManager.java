package com.nhcz500.base.network;

import com.nhcz500.base.network.interceptor.HeadInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nhcz500.base.network.NetConstants.CONNECT_TIMEOUT;

/**
 * 单例管理类
 */
public class RetrofitManager {
    private RetrofitManager(){}
    private static class LazyHolder{
        private static final RetrofitManager INSTANCE=new RetrofitManager();
    }
    public static RetrofitManager getInstance(){
        return LazyHolder.INSTANCE;
    }

    private OkHttpClient getHttpClient(){
        OkHttpClient.Builder build=new OkHttpClient.Builder();
        //连接超时时间
        build.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        //读取超时时间
        build.readTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        //写入超时
        build.writeTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        //head拦截器
        build.addInterceptor(new HeadInterceptor());
        //拦截器

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        build.addInterceptor(logging);

        return build.build();
    }
    private Retrofit retrofit;
    public Retrofit creteRetrofit(){
        if(retrofit!=null&&retrofit.baseUrl().host().equals(NetConstants.BASE_URL)) return retrofit;
         retrofit=new Retrofit.Builder()
                 .baseUrl(NetConstants.BASE_URL)
                 .client(getHttpClient())
                 .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
         return retrofit;
    }


    public<T> T create(Class<T> tClass){
       return creteRetrofit().create(tClass);
    }
}
