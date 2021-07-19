package com.bing.wyqqsplash.base.tools;

import android.view.View;

public class DoubleListener implements View.OnClickListener {
    private final View.OnClickListener listener;
    private long old;

    public DoubleListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        long now = System.currentTimeMillis();
        if (now - old > 1000) {
            if (listener != null) {
                listener.onClick(v);
            }

        }
        old = now;
    }
}
