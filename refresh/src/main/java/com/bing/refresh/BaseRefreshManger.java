package com.bing.refresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public  abstract class BaseRefreshManger {

    public LayoutInflater layoutInflater;

    public BaseRefreshManger(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public abstract View getView();

    public abstract void releaseRefresh();

    public abstract void downRefresh();

    public abstract void idle();

    public abstract void refreshing();

    public abstract void downRefreshPercent(float percent);
}
