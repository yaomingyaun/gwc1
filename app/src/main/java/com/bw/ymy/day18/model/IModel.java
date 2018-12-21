package com.bw.ymy.day18.model;

import com.bw.ymy.day18.callback.MyCallBack;

import java.util.Map;

public interface IModel {

    void  getRequest(String urlstr, Map<String,String> map, Class clazz, MyCallBack callBack);
}
