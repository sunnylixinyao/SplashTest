package com.example.splashtest;


import android.os.Handler;

import java.util.logging.LogRecord;

public class CustomCounterDownTimer implements Runnable {
    private   int time;
    private   int counDowntime;
    private final ICountDownHandler countDownHandler;
    private  final Handler handler;

    private boolean isRun;
    //时时设置SplashActivity界面的TextView的文字,观察者设计模式
    //支持动态传入总时间
    //每过一秒，总秒数减一
    //总时间倒计时为0时，要回调完成状态


    //1.支持动态传入总时间，通过构造函数
    public CustomCounterDownTimer(int time,ICountDownHandler countDownHandler){
        handler = new Handler();
        this.time=time;
        counDowntime=time;
        this.countDownHandler=countDownHandler;


    }

    @Override
    public void run() {
        if (isRun){
            if (handler!=null){
                countDownHandler.onTicker(counDowntime);
            }
            if (counDowntime==0){
                cancel();
                if(countDownHandler!=null){
                    countDownHandler.onFinish();
                }
            }else {
                counDowntime=time--;
                handler.postDelayed(this,1000);
            }
        }
    }


    //设置一个开启的开关
    public void start(){
        isRun=true;
        handler.post(this);//post  run方法
    }

    //设置一个取消的开关
    public void cancel(){
        isRun=false;
        handler.removeCallbacks(this);
    }

    //观察者回调接口
    //定义一个观察者设计模式 的接口
    public   interface ICountDownHandler{
        //时时设置SplashActivity界面的TextView的文字,观察者设计模式
        //倒计时回调
        void onTicker(int time);
        //总时间倒计时为0时，要回调完成状态
        //完成时回调
        void onFinish();
    }
}
