package com.bw.ymy.day18;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class CustomFlowLayout extends LinearLayout {
    //孩子最高的一个
    private  int mChildMaxHeight;
    //左边距
    private  int mHSpace=20;
    //上下边距
    private  int mVspace=20;

    public CustomFlowLayout(Context context) {
        super(context);
    }

    public CustomFlowLayout(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    //测量布局


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //先拿到父容器的宽和高
        int sizeWidth=MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight=MeasureSpec.getSize(heightMeasureSpec);
        //测量孩子大小
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //寻找最高的一个
        findMaxChilderHeight();
        //初始化
        int left=0,top=0;
        //开始循环
        int childCount=getChildCount();
        for (int i=0;i<childCount;i++)
        {
            View view=getChildAt(i);
            //判断是否是第一行的第一个
            if(left!=0)
            {
                if((left+view.getMeasuredWidth())>sizeWidth)
                {
                    top+=mChildMaxHeight+mVspace;
                    left=0;
                }
            }
            left+=view.getMeasuredWidth()+mHSpace;
        }
        setMeasuredDimension(sizeWidth,(top+mChildMaxHeight)>sizeHeight?sizeHeight:top+mChildMaxHeight);
    }

    //绘制

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        findMaxChilderHeight();
        int left=0,top=0;
        //开始循环
        int childCount=getChildCount();
        for (int i=0;i<childCount;i++)
        {
            View view=getChildAt(i);
            //判断是否是第一行的第一个
            if(left!=0)
            {
                if((left+view.getMeasuredWidth())>getWidth())
                {
                    top+=mChildMaxHeight+mVspace;
                    left=0;
                }
            }
            view.layout(left,top,left+view.getMeasuredWidth(),top+getMeasuredHeight());
            left+=view.getMeasuredWidth()+mHSpace;
        }
    }

    //最高的孩子
    private  void findMaxChilderHeight()
    {
        mChildMaxHeight=0;
        int  childCount=getChildCount();
        for (int i=0;i<childCount;i++)
        {
            View view=getChildAt(i);
            if(view.getMeasuredHeight()>mChildMaxHeight)
            {
                mChildMaxHeight=view.getMeasuredHeight();
            }
        }
    }
}
