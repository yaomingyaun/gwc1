package com.bw.ymy.day18.pewsenter;

import com.bw.ymy.day18.callback.MyCallBack;
import com.bw.ymy.day18.model.IModellpl;
import com.bw.ymy.day18.view.IView;

import java.util.Map;

public class IPresenterlpl implements IPresenter {

    private IView iView;
    private IModellpl iModellpl;

    public IPresenterlpl(IView iView) {
        this.iView = iView;
        iModellpl=new IModellpl();
    }
//解绑
    public void  detach()
    {
        iModellpl=null;
        iView=null;
    }
    @Override
    public void getRequest(String urlstr, Map<String, String> map, Class clazz) {
        iModellpl.getRequest(urlstr, map, clazz, new MyCallBack() {
            @Override
            public void onsuccess(Object data) {
                iView.onsuccess(data);
            }
        });

    }}