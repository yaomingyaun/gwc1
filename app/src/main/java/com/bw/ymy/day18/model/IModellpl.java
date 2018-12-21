package com.bw.ymy.day18.model;

import com.bw.ymy.day18.callback.MyCallBack;
import com.bw.ymy.day18.okhttp.ICallBack;
import com.bw.ymy.day18.okhttp.OkHttpNutils;

import java.util.Map;

public class IModellpl implements IModel {
    @Override
    public void getRequest(String urlstr, Map<String, String> map, Class clazz,final MyCallBack callBack) {
        OkHttpNutils.getInstance().PostEnqueue(urlstr, map, clazz, new ICallBack() {
            @Override
            public void onsuccess(Object obj) {
                callBack.onsuccess(obj);
            }

            @Override
            public void onFail(Exception e) {
                callBack.onsuccess(e);

            }
        });
    }
}
