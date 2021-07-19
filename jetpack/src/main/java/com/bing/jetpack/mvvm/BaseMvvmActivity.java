package com.bing.jetpack.mvvm;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseMvvmActivity extends AppCompatActivity {

    public ViewDataBinding viewDataBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MvvmViewInject annotation = this.getClass().getAnnotation(MvvmViewInject.class);
        if (annotation != null) {
            int mainlayoutid = annotation.mainlayoutid();
            int[] ids = annotation.viewModelId();
            Class<? extends BaseMvvmViewModel>[] classes = annotation.viewModelClazz();

            if (mainlayoutid > 0 && ids.length == classes.length) {

                bindView(mainlayoutid, ids, classes);
                afterBindView();


            } else {
                throw new RuntimeException("params error");
            }
        } else {
            throw new RuntimeException("annotation == null");
        }
    }

    private void bindView(int mainlayoutid, int[] ids, Class<? extends BaseMvvmViewModel>[] classes) {
        viewDataBinding = DataBindingUtil.setContentView(this, mainlayoutid);
        viewDataBinding.setLifecycleOwner(this);
        for (int i = 0; i < ids.length; i++) {
            int id = ids[i];
            Class<? extends BaseMvvmViewModel> clazz = classes[i];
            BaseMvvmViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(clazz);
            getLifecycle().addObserver(viewModel);
            viewDataBinding.setVariable(id, viewModel);

            showToast(viewModel);

        }

    }

    private void showToast(BaseMvvmViewModel viewModel) {
        viewModel.getLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(BaseMvvmActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void afterBindView() {

    }


}

