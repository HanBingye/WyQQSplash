package com.bing.wyqqsplash.main.shanghai;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bing.refresh.BaseRefreshManger;
import com.bing.wyqqsplash.R;

public class MeituanRefreshManger extends BaseRefreshManger {

    private ImageView imageView;

    public MeituanRefreshManger(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        View view = layoutInflater.inflate(R.layout.meituan_header_layout, null, false);
        imageView = view.findViewById(R.id.loading);
        return view;
    }

    @Override
    public void releaseRefresh() {
        imageView.setImageResource(R.drawable.meituan_loading_pre);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();

    }

    @Override
    public void downRefresh() {

    }

    @Override
    public void idle() {
        imageView.clearAnimation();
        imageView.setImageResource(R.mipmap.pull_image);
        imageView.setScaleX(0);
        imageView.setScaleY(0);


    }

    @Override
    public void refreshing() {
        imageView.setImageResource(R.drawable.meituan_loading);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();

    }

    @Override
    public void downRefreshPercent(float percent) {
        Log.d("downRefreshPercent", " " + percent);
        imageView.setScaleX(percent);
        imageView.setScaleY(percent);
    }
}
