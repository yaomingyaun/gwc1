package com.bw.ymy.text1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bw.ymy.text1.R;
import com.bw.ymy.text1.bean.YouBean;

import java.util.ArrayList;
import java.util.List;


//右边集合里面嵌套集合
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{
    private List<YouBean.Data> mlist=new ArrayList<>();
    private Context mcontext;

    public Adapter(Context context) {
        this.mcontext = context;
    }
    public void setData(List<YouBean.Data> list) {
        this.mlist = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取布局
        View view=View.inflate(mcontext,R.layout.produce_adapter,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(mlist.get(i).getName());
        //右侧有Recview展示     YouAdapter 把上一个右边展示的Adapter  拿过来
        final  YouAdapter adapter=new YouAdapter(mcontext);
        LinearLayoutManager layoutManager=new LinearLayoutManager(mcontext);
        myViewHolder.mrecyclerView.setLayoutManager(layoutManager);
        myViewHolder.mrecyclerView.setAdapter(adapter);
        myViewHolder.mrecyclerView.addItemDecoration(new DividerItemDecoration(mcontext,DividerItemDecoration.VERTICAL));
       adapter.setData(mlist.get(i).getList());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class  MyViewHolder extends RecyclerView.ViewHolder{


        private TextView name;
        private RecyclerView mrecyclerView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.tv_shop_name);
            mrecyclerView=itemView.findViewById(R.id.recyclerview_shop_product);
        }
    }
}
