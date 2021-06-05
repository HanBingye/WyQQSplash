package com.bing.mvp.mvp.presenter;

import com.bing.mvp.mvp.ILifeCircle;
import com.bing.mvp.mvp.IMvpView;

import java.lang.ref.WeakReference;

public  abstract class LifeCircleMvpPresenter<T extends IMvpView> implements ILifeCircle {

    private WeakReference<T> weakReference;

    protected LifeCircleMvpPresenter(){
        super();
    }
    public LifeCircleMvpPresenter(IMvpView iMvpView){
        attachView(iMvpView);
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        if(weakReference==null){
            weakReference = new WeakReference(iMvpView);
        }else{
            T view = weakReference.get();
            if(view!=iMvpView){
                weakReference=new WeakReference(iMvpView);
            }

        }

    }

    @Override
    public void onDestroy() {
        weakReference=null;
    }
    protected T getView(){
        T view=weakReference!=null?weakReference.get():null;
        if(view==null){
            return getEmptyView();
        }
        return view;
    }

    protected abstract T getEmptyView();
}
