package com.bw.ymy.text1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.ymy.text1.R;
import com.bw.ymy.text1.bean.YouBean;

import java.util.ArrayList;
import java.util.List;

//右边的adapter
public class YouAdapter extends RecyclerView.Adapter<YouAdapter.MyViewHolder> {
    private Context mcontext;
    private List<YouBean.Data.ProductData> mlist=new ArrayList<>();

    //需要注意 不要弄错
    public YouAdapter(Context context) {
        this.mcontext = context;
    }
    public  void setData(List<YouBean.Data.ProductData> list)
    {
        this.mlist=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取布局
        View view=View.inflate(mcontext,R.layout.youadapter,null);
        MyViewHolder viewHolder=new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Glide.with(mcontext).load(mlist.get(i).getIcon()).into(myViewHolder.icon);
        myViewHolder.iname.setText(mlist.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class  MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView icon;
        private TextView iname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.iv_shop_type_product_linear);
            iname=itemView.findViewById(R.id.tv_shop_type_product_linear);
        }
    }
}
