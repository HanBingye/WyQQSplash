package com.bing.wyqqsplash.splash;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

import androidx.annotation.Nullable;

public class MyView extends VideoView {
    //主要用于new出来的对象
    public MyView(Context context) {
        super(context);
    }
//主要用于xml文件,支持自定义属性
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
//也是主要用于xml文件,支持自定义属性,同时支持style样式
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width,height);

    }


}
