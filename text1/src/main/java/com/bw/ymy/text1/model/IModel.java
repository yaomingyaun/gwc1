package com.bw.ymy.text1.model;

import com.bw.ymy.text1.callback.MyCallBack;

import java.util.Map;

public interface IModel {

    void  getRequest(String urlstr, Map<String,String> map, Class clazz, MyCallBack myCallBack);

}
