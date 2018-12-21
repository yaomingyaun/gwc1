package com.bw.ymy.day18.activity;


import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.ymy.day18.Apis;
import com.bw.ymy.day18.Countansts;
import com.bw.ymy.day18.R;
import com.bw.ymy.day18.adapter.showAdapter;
import com.bw.ymy.day18.bean.GoodsBean;
import com.bw.ymy.day18.pewsenter.IPresenterlpl;
import com.bw.ymy.day18.view.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements IView,View.OnClickListener {

    private showAdapter mshowAdapter;
    private CheckBox mIvCircle;
    private List<GoodsBean.DataBean> mList = new ArrayList<>();
    private TextView mAllPriceTxt, nSumPrice;
    private IPresenterlpl iPresenterlpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        iPresenterlpl=new IPresenterlpl(this);
        //获取资源id
        initView();

        getData();

    }
    //解绑

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenterlpl.detach();
    }
    //获取资源id
    public  void  initView()
    {
        mIvCircle = (CheckBox) findViewById(R.id.iv_cricle);
        mAllPriceTxt = (TextView) findViewById(R.id.all_price);
        nSumPrice = (TextView) findViewById(R.id.sum_price_txt);
        mIvCircle.setOnClickListener(this);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mshowAdapter = new showAdapter(this);
        mRecyclerView.setAdapter(mshowAdapter);

        //点击ShopCallBackListener
        mshowAdapter.setListener(new showAdapter.ShopCallBackListener()
          {
            @Override
            public void callBack(List<GoodsBean.DataBean> list) {
                double totalprice=0;
                int num=0;
                int totalNum=0;
                for (int a=0;a<list.size();a++)
                {
                    List<GoodsBean.DataBean.ListBean> listall=list.get(a).getList();
                    for (int i=0;i<listall.size();i++)
                    {
                        totalNum=totalNum+listall.get(i).getNum();
                        if(listall.get(i).isCheck())
                        {
                            totalprice=totalprice+(listall.get(i).getPrice()*listall.get(i).getNum());
                            num=num+listall.get(i).getNum();
                        }
                    }
                }
                if(num<totalNum)
                {
                    //不是全部选中
                    mIvCircle.setChecked(false);
                }else
                {
                    mIvCircle.setChecked(true);
                }
                mAllPriceTxt.setText("合计："+totalprice);
                nSumPrice.setText("去结算(" + num + ")");
            }
        });
    }
    public  void  getData()
    {
        Map<String, String> map = new HashMap<>();
        map.put(Countansts.MAP_KEY_GET_PRODUCT_UID,"71");
        iPresenterlpl.getRequest(Apis.URL_GET_SHOP_CAR_INFO, map, GoodsBean.class);

    }
    @Override
    public void onsuccess(Object data) {
        if(data instanceof  GoodsBean)
        {
            GoodsBean bean= (GoodsBean) data;
            mList=bean.getData();
            if(mList!=null)
            {
                mList.remove(0);
                mshowAdapter.setList(mList);
            }
        }

    }
    //点击事件
    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.iv_cricle:
                checkSeller(mIvCircle.isChecked());
                mshowAdapter.notifyDataSetChanged();
                break;
            default:
        }

    }
    //修改选中状态，获取价格数量
    private  void checkSeller(boolean bool)
    {
        double totalPrice = 0;
        int num = 0;
        for (int a = 0; a < mList.size(); a++) {
            //遍历商家，改变状态
            GoodsBean.DataBean dataBean = mList.get(a);
            dataBean.setCheck(bool);

            List<GoodsBean.DataBean.ListBean> listAll = mList.get(a).getList();
            for (int i = 0; i < listAll.size(); i++) {
                //遍历商品，改变状态
                listAll.get(i).setCheck(bool);
                totalPrice = totalPrice + (listAll.get(i).getPrice() * listAll.get(i).getNum());
                num = num + listAll.get(i).getNum();
            }
        }

        if (bool) {
            mAllPriceTxt.setText("合计：" + totalPrice);
            nSumPrice.setText("去结算(" + num + ")");
        } else {
            mAllPriceTxt.setText("合计：0.00");
            nSumPrice.setText("去结算(0)");
        }
    }


}
