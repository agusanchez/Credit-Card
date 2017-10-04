package com.creditcard.android.mvp.presenter;

import android.util.Log;

import com.creditcard.android.model.CreditCard;
import com.creditcard.android.model.ResponseJsonArray;
import com.creditcard.android.mvp.views.PaymentMethodsMvpView;
import com.creditcard.android.rx.RestHttpObserver;
import com.creditcard.android.service.CreditCardService;
import com.google.gson.Gson;


import java.lang.reflect.Type;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Agustin on 1/10/2017.
 */

public class PaymentMethodsPresenter extends BasePresenter<PaymentMethodsMvpView> {

    public void getPaymentMethods(){
        Subscription subscription = CreditCardService.getInstance().getPaymentMethods()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RestHttpObserver<ResponseJsonArray>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(ResponseJsonArray response) {

                        Type type = new com.google.gson.reflect.TypeToken<List<CreditCard>>(){}.getType();
                        List<CreditCard> creditCardList = (List<CreditCard>) new Gson().fromJson(response.jsonArray, type);

                        getMvpView().onPaymentMethodsFinished(creditCardList);
                    }

                    @Override
                    public void onObserverError(Throwable e) {
                        getMvpView().onError(e);
                    }

                    @Override
                    public void onErrorCode(String message, int errorCode) {
                        getMvpView().onErrorCode(message);
                    }

                    @Override
                    public void onNoInternetConnection() {
                        Log.e("Get Payment Methods","No internet connection");
                        getMvpView().onNoInternetConnection();
                    }
                });
        compositeSubscription.add(subscription);
    }

    public void validateAmount(CreditCard creditCard, String amount){

        int userAmount = Integer.valueOf(amount);

        if(userAmount > Integer.valueOf(creditCard.getMaxAmount())){
            getMvpView().onAmountExceeded();
            return;
        }

        if(userAmount < Integer.valueOf(creditCard.getMinAmount())){
            getMvpView().onAmountMissing();
            return;
        }
        getMvpView().onCorrectAmount(creditCard);
    }
}
