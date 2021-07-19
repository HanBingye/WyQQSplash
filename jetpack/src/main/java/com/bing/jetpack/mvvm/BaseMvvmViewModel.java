package com.bing.jetpack.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;

public class BaseMvvmViewModel extends AndroidViewModel implements LifecycleObserver {
    public MutableLiveData<String> liveData ;
    public BaseMvvmViewModel(@NonNull Application application) {
        super(application);
    }
    //生命周期的绑定
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void onResume() {

    }
    public MutableLiveData<String> getLiveData(){
        if(liveData==null){
            liveData=new MutableLiveData<>();
        }
        return  liveData;
    }
}
