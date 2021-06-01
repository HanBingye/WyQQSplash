package com.bing.wyqqsplash.main;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.airbnb.lottie.LottieAnimationView;
import com.bing.wyqqsplash.R;
import com.bing.wyqqsplash.base.BaseActivity;
import com.bing.wyqqsplash.base.ViewInject;
import com.bing.wyqqsplash.main.beijing.BeijingFrag;
import com.bing.wyqqsplash.main.hangzhou.HangzhouFrag;
import com.bing.wyqqsplash.main.shanghai.ShanghaiFrag;
import com.bing.wyqqsplash.main.shenzhen.ShenzhenFrag;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.OnClick;

@ViewInject(mainlayoutid = R.layout.activity_main)
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.bt_fab)
    FloatingActionButton btFab;


    @BindView(R.id.fl_main_bottom)
    FrameLayout flMainBottom;

    @BindView(R.id.rb_shenzhen)
    RadioButton rbShenzhen;
    @BindView(R.id.rb_beijing)
    RadioButton rbBeijing;
    @BindView(R.id.rg_main_bottom)
    RadioGroup rgMainBottom;
    @BindView(R.id.fl_main_top)
    FrameLayout flMainTop;
    @BindView(R.id.rb_hangzhou)
    LottieAnimationView rbHangzhou;
    @BindView(R.id.rb_shanghai)
    LottieAnimationView rbShanghai;
    @BindView(R.id.rg_main_top)
    LinearLayout rgMainTop;
    private boolean change;
    private BeijingFrag beijingFrag;
    private HangzhouFrag hangzhouFrag;
    private ShanghaiFrag shanghaiFrag;
    private ShenzhenFrag shenzhenFrag;
    private FragmentManager manager;
    private boolean isHangzhouChecked;
    private boolean isShanghaiChecked;


    @Override
    public void afterBindView() {
        initFragment();

        changeAnimation(rgMainBottom, rgMainTop);


    }

    private void initFragment() {
        beijingFrag = new BeijingFrag();
        hangzhouFrag = new HangzhouFrag();
        shanghaiFrag = new ShanghaiFrag();
        shenzhenFrag = new ShenzhenFrag();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl_main_top, beijingFrag);
        transaction.add(R.id.fl_main_top, hangzhouFrag);
        transaction.add(R.id.fl_main_top, shanghaiFrag);
        transaction.add(R.id.fl_main_top, shenzhenFrag);
        transaction.hide(beijingFrag);
        transaction.hide(shanghaiFrag);
        transaction.hide(shenzhenFrag);
        transaction.commit();
        isHangzhouChecked = true;
        rbHangzhou.playAnimation();

//        rgMainTop.setOnCheckedChangeListener(this);
        rgMainBottom.setOnCheckedChangeListener(this);

        rbHangzhou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = manager.beginTransaction();
                showHangzhouFrag(transaction);
                transaction.commit();
                rbHangzhou.playAnimation();
                rbShanghai.reverseAnimation();
                isShanghaiChecked = false;
                isHangzhouChecked = true;


            }
        });
        rbShanghai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = manager.beginTransaction();
                showShanghaiFrag(transaction);
                transaction.commit();
                rbShanghai.playAnimation();
                rbHangzhou.reverseAnimation();
                isHangzhouChecked = false;
                isShanghaiChecked = true;


            }
        });


    }


    @OnClick(R.id.bt_fab)
    public void onClick(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.bt_fab:
                change = !change;
                if (change) {
                    changeAnimation(rgMainTop, rgMainBottom);
                    if (rbShenzhen.isChecked()) {
                        showShenzhenFrag(transaction);
                    }
                    if (rbBeijing.isChecked()) {
                        showBeijingFrag(transaction);
                    }
                } else {
                    changeAnimation(rgMainBottom, rgMainTop);
                    if (isHangzhouChecked) {
                        showHangzhouFrag(transaction);
                    }
                    if (isShanghaiChecked) {
                        showShanghaiFrag(transaction);
                    }
                }
                break;
        }
        transaction.commit();
    }


    private void changeAnimation(ViewGroup gone, ViewGroup show) {
        //消失的动画
        gone.clearAnimation();
        Animation animationGone = AnimationUtils.loadAnimation(this, R.anim.main_rb_gone);
        gone.setAnimation(animationGone);
        gone.setVisibility(View.GONE);
        //展示的动画
        show.clearAnimation();
        Animation animationShow = AnimationUtils.loadAnimation(this, R.anim.main_rb_show);

        show.setAnimation(animationShow);
        show.setVisibility(View.VISIBLE);

    }


    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.rb_beijing:
                showBeijingFrag(transaction);

                break;
//            case R.id.rb_shanghai:
//                showShanghaiFrag(transaction);
//
//                break;
            case R.id.rb_shenzhen:
                showShenzhenFrag(transaction);

                break;
//            case R.id.rb_hangzhou:
//                showHangzhouFrag(transaction);
//
//                break;
        }
        transaction.commit();
    }

    private void showHangzhouFrag(FragmentTransaction transaction) {
        transaction.hide(beijingFrag);
        transaction.hide(shanghaiFrag);
        transaction.hide(shenzhenFrag);
        transaction.show(hangzhouFrag);
    }

    private void showShenzhenFrag(FragmentTransaction transaction) {
        transaction.hide(hangzhouFrag);
        transaction.hide(shanghaiFrag);
        transaction.hide(beijingFrag);
        transaction.show(shenzhenFrag);

    }

    private void showShanghaiFrag(FragmentTransaction transaction) {
        transaction.hide(hangzhouFrag);
        transaction.hide(beijingFrag);
        transaction.hide(shenzhenFrag);
        transaction.show(shanghaiFrag);
    }

    private void showBeijingFrag(FragmentTransaction transaction) {
        transaction.hide(hangzhouFrag);
        transaction.hide(shanghaiFrag);
        transaction.hide(shenzhenFrag);
        transaction.show(beijingFrag);
    }


}