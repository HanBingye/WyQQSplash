package com.bing.wyqqsplash.main.shanghai;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

public class BottomShowBehavior extends CoordinatorLayout.Behavior<TextView> {
    public BottomShowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //这个方法的回调机制  即将发生嵌套滚动时 axes用于判断滑动的方向
    // 当axes== ViewCompat.SCROLL_AXIS_VERTICAL;返回true
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {

        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    //发生嵌套滚动时 回调
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed);

        //向下滑动
        if(dyConsumed+dyUnconsumed>0){
            //隐藏child
            if(child.getVisibility()==View.VISIBLE){
                BottomAnim.hide(child);
            }
            //向上滑动
        }else{
            //展示child
            if(child.getVisibility()!=View.VISIBLE){
                BottomAnim.show(child);
            }
        }
    }
}
