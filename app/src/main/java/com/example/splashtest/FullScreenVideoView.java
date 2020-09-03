package com.example.splashtest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class FullScreenVideoView extends VideoView {
    //主要用于直接new来的对象
    public FullScreenVideoView(Context context) {
        super(context);
    }

    //主要用于xml文件中，支持自定义属性
    public FullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //主要用于xml文件中，支持自定义属性和style样式
    public FullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //测量长宽
    //widthMeasureSpec,获取到宽度的模式
    //测量模式：exactly：精确模式，给定具体数值或者match——parent
    //AT_MOST:最大值模式，当设定为wrap——content，控件大小随着内容的多少而变化
    //UNSPECIFIED：自定义控件时用到，需要多大就设定多大
    //heightMeasureSpec
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width=getDefaultSize(0,widthMeasureSpec);
        //getDefaultSize是view的一个方法
        int heigth=getDefaultSize(0,heightMeasureSpec);
        setMeasuredDimension(width,heigth);
        //videoView会将宽度和高度进行调整，因此不能得到全屏
        //复写onMeasure方法是为了重新写setMeasuredDimension方法
        //得到原始宽高
    }

}
