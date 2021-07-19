package com.bing.wyqqsplash.base.tools;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class AnimationUtils {
    public static void translationX(View view, float start, float end, Animator.AnimatorListener listener) {

       
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationX", start, end);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
        if(listener!=null){
            objectAnimator.addListener(listener);
        }

    }
}
