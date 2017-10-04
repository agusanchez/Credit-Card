package com.creditcard.android.mvp.presenter;

import com.creditcard.android.mvp.views.MvpView;
import com.creditcard.android.mvp.views.Presenter;

import rx.subscriptions.CompositeSubscription;


public class BasePresenter<T extends MvpView> implements Presenter<T> {

    protected T mvpView;
    protected CompositeSubscription compositeSubscription;


    @Override
    public void attachMvpView(T t) {
        this.mvpView = t;
        this.compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void dettachMvpView() {
        this.mvpView = null;
        if (this.compositeSubscription != null)
            this.compositeSubscription.clear();
    }

    public T getMvpView() {
        return this.mvpView;
    }
}
