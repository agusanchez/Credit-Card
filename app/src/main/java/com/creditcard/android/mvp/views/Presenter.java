package com.creditcard.android.mvp.views;


public interface Presenter<MvpView> {

    void attachMvpView(MvpView mvpView);

    void dettachMvpView();
}
