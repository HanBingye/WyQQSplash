package com.bing.wyqqsplash.base;

import android.app.Application;

import com.bing.wyqqsplash.base.context.MContext;

public class EntryApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        CrashProtectManager.getInstance(this).init();
        MContext.getInstance().init(this);
    }
}
