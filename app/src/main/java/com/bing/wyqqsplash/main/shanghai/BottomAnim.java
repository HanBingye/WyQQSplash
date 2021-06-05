package com.bing.wyqqsplash.main.shanghai;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bing.wyqqsplash.R;

public class BottomAnim {
    public static void hide(View view){
        view.clearAnimation();
        Animation animationGone = AnimationUtils.loadAnimation(view.getContext(), R.anim.main_rb_gone);
        view.setAnimation(animationGone);
        view.setVisibility(View.INVISIBLE);
    }
    public static void show(View view){
        view.clearAnimation();
        Animation animationShow = AnimationUtils.loadAnimation(view.getContext(), R.anim.main_rb_show);

        view.setAnimation(animationShow);
        view.setVisibility(View.VISIBLE);
    }
}
