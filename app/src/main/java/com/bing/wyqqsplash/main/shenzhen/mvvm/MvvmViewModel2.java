package com.bing.wyqqsplash.main.shenzhen.mvvm;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.bing.jetpack.mvvm.BaseMvvmViewModel;

public class MvvmViewModel2 extends BaseMvvmViewModel {
    public MutableLiveData<String> name2=new MutableLiveData<>();

    public MvvmViewModel2(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void onResume() {
        name2.setValue("MvvmViewModel2");
    }

    public void tvOnclick2(View view) {
        name2.setValue("tvOnclick2");
        getLiveData().setValue("tvOnclick2");

    }
}
