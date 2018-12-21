package com.bw.ymy.day18.adapter;

//商家的适配器

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bw.ymy.day18.R;
import com.bw.ymy.day18.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;
//展示商家
public class showAdapter  extends RecyclerView.Adapter<showAdapter.MyViewHolder> {
    private List<GoodsBean.DataBean> mlist=new ArrayList<>();
    private Context mcontext;

    public showAdapter(Context context) {
        this.mcontext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mcontext,R.layout.shop_seller,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        //设置商家的名字
        myViewHolder.mSell.setText(mlist.get(i).getSellerName());
        //重要
        final productsAdapter productsAdapter=new productsAdapter(mcontext,mlist.get(i).getList());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mcontext);
        myViewHolder.mRecyclerView.setLayoutManager(linearLayoutManager);
        //重要
       myViewHolder.mRecyclerView.setAdapter(productsAdapter);

       myViewHolder.mcheck.setChecked(mlist.get(i).isCheck());

       productsAdapter.setListener(new productsAdapter.ShopCallBackListener() {
           @Override
           public void callBack() {
               if(mShopCallBackListener!=null)
               {
                   mShopCallBackListener.callBack(mlist);
               }
               List<GoodsBean.DataBean.ListBean> listBeans=mlist.get(i).getList();
               boolean isAllCheck=true;
               for (GoodsBean.DataBean.ListBean bean:listBeans)
               {
                   if(!bean.isCheck())
                   {
                       //只要由一个商品未选择，标志设为false,跳出循环
                       isAllCheck=false;
                       break;
                   }
               }
               //刷新
               myViewHolder.mcheck.setChecked(isAllCheck);
               mlist.get(i).setCheck(isAllCheck);
           }
       });
       //商品所有的状态
        myViewHolder.mcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //改变标志的位置
                mlist.get(i).setCheck(myViewHolder.mcheck.isChecked());
                //调用产品adapter的方法，用来全选he反选
                productsAdapter.selectOrRemoveAll(myViewHolder.mcheck.isChecked());
            }
        });


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
    public  void  setList(List<GoodsBean.DataBean> list)
    {
        this.mlist=list;
        notifyDataSetChanged();
    }



    public class  MyViewHolder extends  RecyclerView.ViewHolder{
        RecyclerView mRecyclerView;
        TextView mSell;
        CheckBox mcheck;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mSell=itemView.findViewById(R.id.tv_shop);
            mcheck=itemView.findViewById(R.id.check_shop);
            mRecyclerView=itemView.findViewById(R.id.recycler_shop);
        }
    }
    private ShopCallBackListener mShopCallBackListener;

    public void setListener(ShopCallBackListener listener) {
        this.mShopCallBackListener = listener;
    }

    public interface ShopCallBackListener {
        void callBack(List<GoodsBean.DataBean> list);
    }
}
