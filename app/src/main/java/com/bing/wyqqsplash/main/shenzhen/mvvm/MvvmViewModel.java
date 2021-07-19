package com.bing.wyqqsplash.main.shenzhen.mvvm;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.bing.jetpack.mvvm.BaseMvvmViewModel;

public class MvvmViewModel extends BaseMvvmViewModel {

    public ObservableField<String> name = new ObservableField<>();

    public MvvmViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void onResume() {
        name.set("MvvmViewModel");
    }

    public void tvOnclick(View view) {
        name.set("tvOnclick");
        getLiveData().setValue("tvOnclick");

    }

}
