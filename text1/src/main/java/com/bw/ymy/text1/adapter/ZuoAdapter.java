package com.bw.ymy.text1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.ymy.text1.R;
import com.bw.ymy.text1.bean.ZuoBean;

import java.util.ArrayList;
import java.util.List;
//左边的Adapter
public class ZuoAdapter extends RecyclerView.Adapter<ZuoAdapter.ViewHolder> {

    private Context mcontext;
    private List<ZuoBean.DataBean> mlist=new ArrayList<>();

    public ZuoAdapter(Context context) {
        this.mcontext = context;
    }
    public  void  setData(List<ZuoBean.DataBean> list)
    {
        this.mlist=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取布局
        View view=LayoutInflater.from(mcontext).inflate(R.layout.zuoadapter,null);
        ViewHolder viewHolder1=new ViewHolder(view);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        //显示名字
       viewHolder.name.setText(mlist.get(i).getName());
       //点击进去显示更多
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnClickListener != null){
                    //getcid  bean类是int 需要自己改成String
                    mOnClickListener.onClick(i, mlist.get(i).getCid());

            }
            }
        });

    }
    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
      private   LinearLayout linearLayout;
        private  TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.type_name);
            linearLayout=itemView.findViewById(R.id.ll_shop_type);
        }
    }
    //点击接口
    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener listener) {
        this.mOnClickListener = listener;
    }

    public interface OnClickListener {
        void onClick(int position, String cid);
    }
}
