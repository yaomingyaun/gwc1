package com.bw.ymy.day18.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.bw.ymy.day18.R;
import com.bw.ymy.day18.adapter.productsAdapter;
import com.bw.ymy.day18.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;


//数量 +  -
public class CountView extends RelativeLayout implements View.OnClickListener {
    private EditText editCha;
    private ImageView add,jian;

    public CountView(Context context) {
        super(context);
        init(context);
    }

    public CountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
        private  Context context;
    private  void init(Context context)
    {
            this.context=context;

            View view=View.inflate(context, R.layout.shop_count,null);
              add =  view.findViewById(R.id.add_car);
              jian =  view.findViewById(R.id.jian_car);
             editCha=view.findViewById(R.id.edit_shop_car);
             add.setOnClickListener(this);
             jian.setOnClickListener(this);
             addView(view);
             editCha.addTextChangedListener(new TextWatcher() {
                 @Override
                 public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                 }

                 @Override
                 public void onTextChanged(CharSequence s, int start, int before, int count) {

                 }

                 @Override
                 public void afterTextChanged(Editable s) {

                 }
             });

    }

    private  int num;

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            //点击改变数量
            case R.id.add_car:
                num++;
                editCha.setText(num+"");
                list.get(position).setNum(num);
                listener.callBack();
                productsAdapter.notifyItemChanged(position);
                break;
            case  R.id.jian_car:
                if(num>1)
                {
                    num--;
                }else
                {
                    toast("不能再减了！");
                }
                editCha.setText(num+"");
                list.get(position).setNum(num);
                listener.callBack();
                productsAdapter.notifyItemChanged(position);
                break;
            default:
                break;
        }

    }
    //吐司
    private void toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    //传递的数据
    private List<GoodsBean.DataBean.ListBean> list = new ArrayList<>();
    private int position;
    private productsAdapter productsAdapter;


    //隐藏 显示
    public void setData(productsAdapter productsAdapter, List<GoodsBean.DataBean.ListBean> list, int i) {

        this.list=list;
        this.productsAdapter=productsAdapter;
        position=i;
        num=list.get(i).getNum();
        editCha.setText(num+"");

    }
    private CallBackListener listener;

    public void setOnCallBack(CallBackListener listener) {
        this.listener = listener;
    }

    public interface CallBackListener {
        void callBack();
    }
}














































































