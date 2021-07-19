package com.bing.wyqqsplash.base.context;

import android.app.Application;
import android.content.Context;

public class MContext {
    private static MContext instance;
    private Application application;


    public static MContext getInstance() {
        if (instance == null) {
            instance = new MContext();

        }
        return instance;
    }

    public void init(Application application) {
        this.application = application;
    }

    public Context getMContext() {
        return application.getApplicationContext();
    }
}
