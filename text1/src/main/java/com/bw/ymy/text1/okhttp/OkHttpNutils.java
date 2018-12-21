package com.bw.ymy.text1.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpNutils {
    //单列
    private static  OkHttpNutils instance;
    private Handler handler=new Handler(Looper.myLooper());
    private OkHttpClient httpClient;

    public static  OkHttpNutils getInstance() {
        if (instance == null) {
            {
                synchronized (OkHttpNutils.class) {
                    if (null == instance) {
                        instance = new OkHttpNutils();
                    }
                }

            }
        }
        return instance;
    }
            //构造方法
    private OkHttpNutils(){
        //打印日志
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient=new OkHttpClient.Builder()
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

    }
    //POST  请求

    public  void PostEnqueue(String urlstr, Map<String,String> map, final Class clazz, final ICallBack callBack)
    {
        FormBody.Builder builder=new FormBody.Builder();
        for (Map.Entry<String,String> entry:map.entrySet())
        {
            builder.add(entry.getKey(),entry.getValue());
        }
        RequestBody bulider=builder.build();
        Request builder1=new Request.Builder()
                .post(bulider)
                .url(urlstr)
                .build();
        Call call=httpClient.newCall(builder1);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFail(e);
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String result=response.body().string();
                final Object o=new Gson().fromJson(result,clazz);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onsuccess(o);

                    }
                });

            }
        });
    }
}