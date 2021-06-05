package com.bing.wyqqsplash.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.bing.wyqqsplash.R;
import com.bing.wyqqsplash.base.BaseActivity;
import com.bing.wyqqsplash.base.ViewInject;
import com.bing.wyqqsplash.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

@ViewInject(mainlayoutid = R.layout.activity_splash)
public class SplashActivity extends BaseActivity {


    @BindView(R.id.vv_splash)
    MyView vvSplash;
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    private SplashTimePresenter splashTimePresenter;



    @Override
    public void afterBindView() {
        initSplashTimePresenter();


        initVideo();
    }

    private void initSplashTimePresenter() {
        splashTimePresenter = new SplashTimePresenter(this);
        splashTimePresenter.initTimer();

    }

    private void initVideo() {
        vvSplash.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.movie));
        vvSplash.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        vvSplash.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashTimePresenter.onDestroy();


    }

    public void setTvText(String s) {
        tvSkip.setText(s);
    }

    @OnClick(R.id.tv_skip)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_skip:
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
                break;
        }

    }


}