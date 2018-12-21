package com.bw.ymy.text1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.ymy.text1.adapter.Adapter;
import com.bw.ymy.text1.adapter.ZuoAdapter;
import com.bw.ymy.text1.bean.YouBean;
import com.bw.ymy.text1.bean.ZuoBean;
import com.bw.ymy.text1.presenter.IPresenterlpl;
import com.bw.ymy.text1.view.IView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView {

    private IPresenterlpl iPresenterlpl;
    private ZuoAdapter zuoAdapter;
    private Adapter adapter;
    private RecyclerView recyclerView1,recyclerView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iPresenterlpl=new IPresenterlpl(this);
        //获取资源id
        initView();
        //展示左侧数据
        Lefttype();
        //右侧
        getData();
    }
    //右侧
    private void getData() {

        recyclerView2=findViewById(R.id.recyclerView_you);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        adapter= new Adapter(this);
        recyclerView2.setAdapter(adapter);
    }
    //左侧
    private void initView() {
        recyclerView1=findViewById(R.id.recyclerView_zuo);
        LinearLayoutManager layoutManagerleft=new LinearLayoutManager(this);
        layoutManagerleft.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(layoutManagerleft);
        recyclerView1.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        zuoAdapter=new ZuoAdapter(this);
        recyclerView1.setAdapter(zuoAdapter);
        //点击显示右边的数据
        zuoAdapter.setOnClickListener(new ZuoAdapter.OnClickListener() {
            @Override
            public void onClick(int position, String cid) {
                righttype(cid);
            }
        });
    }
    //左边
    private void Lefttype() {
        Map<String,String> map=new HashMap<>();
        iPresenterlpl.getRequest(Apis.URL_PRODUCT_GET_CATAGORY,map,ZuoBean.class);
    }
    //右边
    private void righttype(String cid) {
        Map<String,String> map=new HashMap<>();
        map.put(Uid.MAP_KEY_PRODUCT_GET_CATAGORY_CID,cid);
        iPresenterlpl.getRequest(Apis.URL_PRODUCT_GET_PRODUCT_CATAGORY,map,YouBean.class);
    }

    @Override
    public void onSuccess(Object data) {
        //左边
        if(data instanceof ZuoBean)
        {
            ZuoBean zuoBean= (ZuoBean) data;
           zuoAdapter.setData(zuoBean.getData());
            //  //右边
        }else  if(data instanceof  YouBean)
        {
            YouBean  youBean= (YouBean) data;
            adapter.setData(youBean.getData());
        }
    }
}
