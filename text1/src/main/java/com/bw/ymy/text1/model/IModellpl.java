package com.bw.ymy.text1.model;

import com.bw.ymy.text1.callback.MyCallBack;
import com.bw.ymy.text1.okhttp.ICallBack;
import com.bw.ymy.text1.okhttp.OkHttpNutils;

import java.util.Map;

public class IModellpl implements IModel {
    @Override
    public void getRequest(String urlstr, Map<String, String> map, Class clazz, final MyCallBack myCallBack) {

        OkHttpNutils.getInstance().PostEnqueue(urlstr, map, clazz, new ICallBack() {
            @Override
            public void onsuccess(Object obj) {
                myCallBack.onsuccess(obj);
            }

            @Override
            public void onFail(Exception e) {
                myCallBack.onsuccess(e);

            }
        });
    }
}
