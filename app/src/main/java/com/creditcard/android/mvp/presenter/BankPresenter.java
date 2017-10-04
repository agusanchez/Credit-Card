package com.creditcard.android.mvp.presenter;

import android.util.Log;

import com.creditcard.android.model.Bank;
import com.creditcard.android.model.ResponseJsonArray;
import com.creditcard.android.mvp.views.BankMvpView;
import com.creditcard.android.rx.RestHttpObserver;
import com.creditcard.android.service.CreditCardService;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Agustin on 2/10/2017.
 */

public class BankPresenter extends BasePresenter<BankMvpView>{

    public void getBanks(String paymentMethodId){
        Subscription subscription = CreditCardService.getInstance().getBanks(paymentMethodId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RestHttpObserver<ResponseJsonArray>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(ResponseJsonArray response) {

                        Type type = new com.google.gson.reflect.TypeToken<List<Bank>>(){}.getType();
                        List<Bank> bankList = (List<Bank>) new Gson().fromJson(response.jsonArray,type);

                        if(bankList.isEmpty()){
                            getMvpView().onEmptyBankList();
                        } else {
                            getMvpView().onBankFinished(bankList);
                        }
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
                        Log.e("Get Banks","No internet connection");
                        getMvpView().onNoInternetConnection();
                    }
                });
        compositeSubscription.add(subscription);
    }
}
