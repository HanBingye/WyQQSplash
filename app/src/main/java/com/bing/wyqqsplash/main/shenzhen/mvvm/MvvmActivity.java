package com.bing.wyqqsplash.main.shenzhen.mvvm;

import androidx.databinding.library.baseAdapters.BR;

import com.bing.jetpack.mvvm.BaseMvvmActivity;
import com.bing.jetpack.mvvm.MvvmViewInject;
import com.bing.wyqqsplash.R;


@MvvmViewInject(mainlayoutid = R.layout.activity_mvvm, viewModelId = {BR.mvvm, BR.mvvm2}, viewModelClazz = {MvvmViewModel.class, MvvmViewModel2.class})
public class MvvmActivity extends BaseMvvmActivity {

}
