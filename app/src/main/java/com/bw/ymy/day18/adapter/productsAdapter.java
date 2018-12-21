package com.bw.ymy.day18.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.ymy.day18.R;
import com.bw.ymy.day18.bean.GoodsBean;
import com.bw.ymy.day18.view.CountView;

import java.util.ArrayList;
import java.util.List;

//展示商家里面的的适配器
public class productsAdapter  extends RecyclerView.Adapter<productsAdapter.MyViewHolder>{
    private List<GoodsBean.DataBean.ListBean> mlist=new ArrayList<>();
    private Context mcontext;

    public productsAdapter(Context context,List<GoodsBean.DataBean.ListBean> list) {
        this.mcontext = context;
        this.mlist=list;
    }
    @NonNull
    @Override
    public  MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //调用
        View view=View.inflate(mcontext,R.layout.adapter,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
  public void onBindViewHolder(@NonNull MyViewHolder myViewHolder,final int i) {
         String url = mlist.get(i).getImages().split("\\|")[0].replace("https", "http");
          Glide.with(mcontext).load(url).into(myViewHolder.mImage);

        myViewHolder.mTitle.setText(mlist.get(i).getTitle());
        myViewHolder.mPrice.setText(mlist.get(i).getPrice() + "");

            //根据我的记录状态，改变勾选
        myViewHolder.mCheckBox.setChecked(mlist.get(i).isCheck());

        //重要1
        //myViewHolder.mCustomShopCarPrice.setData(this,mlist,i);
        myViewHolder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //优先改变自己的状态
                mlist.get(i).setCheck(isChecked);
                if(mShopCallBackListener!=null)
                {
                    mShopCallBackListener.callBack();
                }
            }
        });

        //重要2   两者 缺一不可  这一个不写  size 为0
        myViewHolder.mCustomShopCarPrice.setData(this,mlist,i);


        myViewHolder.mCustomShopCarPrice.setOnCallBack(new CountView.CallBackListener() {
            @Override
            public void callBack() {
                if (mShopCallBackListener != null) {
                    mShopCallBackListener.callBack();
                }
            }
        });
    }

  @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class  MyViewHolder extends RecyclerView.ViewHolder{
        CountView mCustomShopCarPrice;
        TextView mTitle, mPrice;
        ImageView mImage;
        CheckBox mCheckBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.iv_product);
            mTitle = (TextView) itemView.findViewById(R.id.tv_product_title);
            mPrice = (TextView) itemView.findViewById(R.id.tv_product_price);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.check_product);
            mCustomShopCarPrice = (CountView) itemView.findViewById(R.id.custom_product_counter);
        }
    }

    //修改全选/反选
    public void selectOrRemoveAll(boolean isSelectAll) {
        for (GoodsBean.DataBean.ListBean listBean : mlist) {
            listBean.setCheck(isSelectAll);
        }
        notifyDataSetChanged();
    }

    private ShopCallBackListener mShopCallBackListener;

    public void setListener(ShopCallBackListener listener) {
        this.mShopCallBackListener = listener;
    }

    public interface ShopCallBackListener {
        void callBack();


    }
}
