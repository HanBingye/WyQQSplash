package com.bing.mvp.mvp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public interface ILifeCircle {
    void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onReStart();

    void onNewIntent(Intent intent);

    void onSaveInstanceState( Bundle bundle);

    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);

    void onActivityCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments);

    void attachView(IMvpView iMvpView);

    void onDestroyView();
}
