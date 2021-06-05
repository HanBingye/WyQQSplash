package com.bing.wyqqsplash.main.shanghai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ShanghaiViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<String> list = new ArrayList<>();

    public ShanghaiViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);


        list.add("知乎");
        list.add("即刻");
        list.add("下拉刷新");

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ZhihuFrag();
            case 1:
                return new JikeFrag();
            case 2:
                return new RefreshFrag();


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
