package com.bing.wyqqsplash.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null) {
            int mainlayoutid = annotation.mainlayoutid();
            if (mainlayoutid > 0) {
                view = inflater.inflate(mainlayoutid, container, false);
                bindView(view);
                afterBindView();


            } else {
                throw new RuntimeException("mainlayoutid<0");
            }
        } else {
            throw new RuntimeException("annotation == null");
        }
        return view;
    }

    private void bindView(View view) {
        ButterKnife.bind(this, view);
    }


    public abstract void afterBindView();


}
