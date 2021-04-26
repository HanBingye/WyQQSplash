package com.bing.wyqqsplash.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null) {
            int mainlayoutid = annotation.mainlayoutid();
            if (mainlayoutid > 0) {
                setContentView(mainlayoutid);
                bindView();
                afterBindView();


            } else {
                throw new RuntimeException("mainlayoutid<0");
            }
        } else {
            throw new RuntimeException("annotation == null");
        }
    }

    public abstract void afterBindView() ;

    private void bindView() {
        ButterKnife.bind(this);
    }


}
