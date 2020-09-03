package com.example.splashtest;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class SplashActivity extends AppCompatActivity {
    private FullScreenVideoView mvideoView;
    private TextView mtextView;
    private CustomCounterDownTimer customCounterDownTimer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mvideoView=(FullScreenVideoView) findViewById(R.id.VV_play);
        mtextView=(TextView)findViewById(R.id.tv_splash_timer);
        //观察者设计模式
        mtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示启动一个Activity
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        });
        mvideoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+ File.separator+R.raw.splash));
        //播放器有其状态，刚开始是闲置状态，然后是准备状态，在准备状态之后让其播放
        //观察者观察播放器是否处于准备状态
        //观察者设计模式，界面就是观察者，被观察者是播放器
        mvideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            //匿名内部类，IOC数据回调
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        //观察播放器是否播放完毕
        mvideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
        //数据回调
         customCounterDownTimer=new CustomCounterDownTimer(5, new CustomCounterDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                mtextView.setText(time+"秒");
            }

            @Override
            public void onFinish() {
                mtextView.setText("跳过");
            }
        });
        customCounterDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        customCounterDownTimer.cancel();
    }
}
