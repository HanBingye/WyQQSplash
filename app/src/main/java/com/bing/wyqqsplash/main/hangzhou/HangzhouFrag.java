package com.bing.wyqqsplash.main.hangzhou;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bing.wyqqsplash.R;
import com.bing.wyqqsplash.base.BaseFragment;
import com.bing.wyqqsplash.base.ViewInject;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

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

    @Override
    public void afterBindView() {
        initRecyclerView();
        initListener();

    }

    private void initRecyclerView() {
        rvHangzhou.setLayoutManager(new LinearLayoutManager(getContext()));

        rvHangzhou.setAdapter(new HangzhouAdapter(getActivity(),HangzhouData.getData()));
    }


    private void initListener() {
        appbarHangzhou.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (-verticalOffset < appBarLayout.getMeasuredHeight() / 2) {
                    tvHangzhouTitle.setVisibility(View.INVISIBLE);
                } else {
                    tvHangzhouTitle.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}