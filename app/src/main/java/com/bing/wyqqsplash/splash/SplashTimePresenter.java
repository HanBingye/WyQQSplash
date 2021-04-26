package com.bing.wyqqsplash.splash;

import com.bing.wyqqsplash.mvp.IMvpView;
import com.bing.wyqqsplash.mvp.base.BaseMvpPresenter;

public class SplashTimePresenter extends BaseMvpPresenter {
    private SplashActivity activity;
    private Timer timer;

    public SplashTimePresenter(SplashActivity activity) {
        this.activity = activity;
    }

    public void initTimer() {
        timer = new Timer(5, new Timer.MyCallback() {
            @Override
            public void onRun(int time) {
                activity.setTvText(time + "秒");
            }

            @Override
            public void finish() {
                activity.setTvText("跳过");

            }
        });
        timer.start();
    }

    public void cancel() {
        timer.cancel();
    }

    @Override
    protected IMvpView getEmptyView() {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cancel();
    }
}
