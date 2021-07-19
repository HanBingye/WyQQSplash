package com.bing.wyqqsplash.main.hangzhou;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bing.player.PlayerService;
import com.bing.player.source.RawPlaySource;
import com.bing.wyqqsplash.R;
import com.bing.wyqqsplash.base.BaseFragment;
import com.bing.wyqqsplash.base.ViewInject;
import com.bing.wyqqsplash.base.context.MContext;
import com.bing.wyqqsplash.base.tools.AnimationUtils;
import com.bing.wyqqsplash.base.tools.DoubleListener;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.fragment_hangzhou)
public class HangzhouFrag extends BaseFragment {


    @BindView(R.id.tv_hangzhou_title)
    TextView tvHangzhouTitle;
    @BindView(R.id.ct_bar_hangzhou)
    CollapsingToolbarLayout ctBarHangzhou;
    @BindView(R.id.appbar_hangzhou)
    AppBarLayout appbarHangzhou;
    @BindView(R.id.rv_hangzhou)
    RecyclerView rvHangzhou;
    @BindView(R.id.tv_pmd)
    TextView tvPmd;
    @BindView(R.id.iv_hangzhou_bg)
    ImageView ivHangzhouBg;
    private boolean IsPlaying;
    private PlayerService playerService;

    @Override
    public void afterBindView() {
        tvPmd.setSelected(true);
        initGlide();
        initRecyclerView();
        initListener();



    }

    private void initGlide() {

        Glide.with(this)
                .load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_match%2F0%2F12060417613%2F0.jpg&refer=http%3A%2F%2Finews.gtimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1627008638&t=3d079dbda2883e44faf841e946759f51")
                .thumbnail(0.25f)
                .placeholder(R.mipmap.hangzhou)
                .into(ivHangzhouBg);
    }

    private void initRecyclerView() {
        rvHangzhou.setLayoutManager(new LinearLayoutManager(getContext()));

        rvHangzhou.setAdapter(new HangzhouAdapter(getActivity(), HangzhouData.getData()));
    }


    private void initListener() {
        appbarHangzhou.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (-verticalOffset < appBarLayout.getMeasuredHeight() / 2) {
                    tvHangzhouTitle.setVisibility(View.INVISIBLE);
                    tvPmd.setVisibility(View.INVISIBLE);
                } else {
                    tvHangzhouTitle.setVisibility(View.VISIBLE);
                    if (IsPlaying) {
                        tvPmd.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        tvHangzhouTitle.setOnClickListener(new DoubleListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHangzhouTitle.clearAnimation();
                tvPmd.clearAnimation();
                if (IsPlaying) {
                    tvPmd.setVisibility(View.INVISIBLE);
                    AnimationUtils.translationX(tvHangzhouTitle, tvHangzhouTitle.getTranslationX(), tvHangzhouTitle.getTranslationX() + 120, null);
                    AnimationUtils.translationX(tvPmd, tvPmd.getTranslationX(), tvPmd.getTranslationX() + 120, null);
                    playOrPause();
                } else {
                    AnimationUtils.translationX(tvHangzhouTitle, tvHangzhouTitle.getTranslationX(), tvHangzhouTitle.getTranslationX() - 120, null);
                    AnimationUtils.translationX(tvPmd, tvPmd.getTranslationX(), tvPmd.getTranslationX() - 120, new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            tvPmd.setVisibility(View.VISIBLE);
                            bindService(MContext.getInstance().getMContext());
                        }
                    });
                }
                IsPlaying = !IsPlaying;

            }
        }));

    }

    ServiceConnection connection = new ServiceConnection() {
        //和service链接成功后调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PlayerService.PlayerBinder binder = (PlayerService.PlayerBinder) service;
            playerService = binder.getService();
            playOrPause();
        }

        //service链接断开后调用,在正常情况下是不被调用的,
        // 它的调用时机是当service服务被意外销毁时,例如内存的资源不足时
        @Override
        public void onServiceDisconnected(ComponentName name) {
            if (playerService != null) {
                playerService.unbindService(connection);
                playerService = null;
                Log.d("onServiceDisconnected", "onServiceDisconnected: ");
            }
        }
    };

    private void bindService(Context context) {
        if (playerService != null) {
            playOrPause();
        } else {
            Intent intent = new Intent(context, PlayerService.class);
            context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }

    }

    public void playOrPause() {
        if (playerService != null) {
            playerService.playOrPause(new RawPlaySource().setPath(MContext.getInstance().getMContext().getPackageName(), R.raw.honor), MContext.getInstance().getMContext());

        }
    }


}