package com.bing.wyqqsplash.main.shanghai;

import androidx.viewpager.widget.ViewPager;

import com.bing.wyqqsplash.R;
import com.bing.wyqqsplash.base.BaseFragment;
import com.bing.wyqqsplash.base.ViewInject;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.fragment_shanghai)
public class ShanghaiFrag extends BaseFragment {


    @BindView(R.id.tl_tabLayout)
    TabLayout tlTabLayout;
    @BindView(R.id.vp_viewpager)
    ViewPager vpViewpager;

    @Override
    public void afterBindView() {
        tlTabLayout.setupWithViewPager(vpViewpager);
        vpViewpager.setAdapter(new ShanghaiViewPagerAdapter(getChildFragmentManager()));


    }
}