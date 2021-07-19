package com.bing.wyqqsplash.main.shenzhen;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.bing.wyqqsplash.R;
import com.bing.wyqqsplash.base.BaseFragment;
import com.bing.wyqqsplash.base.ViewInject;
import com.bing.wyqqsplash.main.hangzhou.HangzhouDetailActivity;
import com.bing.wyqqsplash.main.shenzhen.mvvm.MvvmActivity;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.fragment_shenzhen)
public class ShenzhenFrag extends BaseFragment {


    @BindView(R.id.bt_shenzhen)
    Button btShenzhen;
    @BindView(R.id.bt_weixin)
    Button btWeixin;
    @BindView(R.id.bt_safe)
    Button btSafe;
    @BindView(R.id.bt_jetPack)
    Button btJetPack;

    @Override
    public void afterBindView() {

        btShenzhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HangzhouDetailActivity.start(getActivity(), btShenzhen);

            }
        });
        btWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WeixinActivity.class));
            }
        });
        btSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SafeActivity.class));
            }
        });
        btJetPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                startActivity(new Intent(getContext(), MvvmActivity.class));
            }
        });
    }
}