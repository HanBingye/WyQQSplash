package com.bing.wyqqsplash.splash;

import android.os.Handler;

public class Timer implements Runnable {
    private int time;
    private MyCallback myCallback;

    private boolean isRun;
    private Handler handler;

    public Timer(int time, MyCallback myCallback) {
        this.time = time;
        this.myCallback = myCallback;
        handler = new Handler();

    }

    @Override
    public void run() {
        if (isRun) {
            if (handler != null) {
                myCallback.onRun(time);
            }
            if (time == 0) {
                cancel();
                if (handler != null) {
                    myCallback.finish();
                }
            } else {
                time--;
                handler.postDelayed(this, 1000);
            }
        }
    }

    public void start() {
        isRun = true;
        handler.post(this);
    }

    public void cancel() {
        isRun = false;
        handler.removeCallbacks(this);
    }

    public interface MyCallback {
        void onRun(int time);

        void finish();
    }

}
