package com.bing.wyqqsplash.main.hangzhou;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;

import com.bing.wyqqsplash.R;
import com.bing.wyqqsplash.base.BaseActivity;
import com.bing.wyqqsplash.base.ViewInject;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.activity_hangzhou_detail)
public class HangzhouDetailActivity extends BaseActivity {
    @BindView(R.id.iv_hangzhou_detail)
    ImageView ivHangzhouDetail;
    private static String s = "HangzhouDetailActivity";

    @Override
    public void afterBindView() {
        initAnimation();
    }

    private void initAnimation() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            ViewCompat.setTransitionName(ivHangzhouDetail,s);
            //开启转场动画
            startPostponedEnterTransition();
        }
    }



    public static void start(Activity activity, View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(activity, HangzhouDetailActivity.class);
            Pair pair = new Pair(v, s);
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair);
            ActivityCompat.startActivity(activity, intent, activityOptionsCompat.toBundle());
        }
    }
}