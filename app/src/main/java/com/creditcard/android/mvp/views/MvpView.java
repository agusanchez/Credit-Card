package com.creditcard.android.mvp.views;

import android.content.Context;


public interface MvpView {

    Context getMvpContext();

    void onError(Throwable throwable);

    void onErrorCode(String message);

    void onNoInternetConnection();
}
