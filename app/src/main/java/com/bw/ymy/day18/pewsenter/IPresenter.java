package com.bw.ymy.day18.pewsenter;

import com.bw.ymy.day18.callback.MyCallBack;

import java.util.Map;

public interface IPresenter {
    void  getRequest(String urlstr, Map<String,String> map, Class clazz);
}
