package com.creditcard.android.mvp.presenter;

import android.util.Log;

import com.creditcard.android.model.Installment;
import com.creditcard.android.model.ResponseJsonArray;
import com.creditcard.android.mvp.views.InstallmentMvpView;
import com.creditcard.android.rx.RestHttpObserver;
import com.creditcard.android.service.CreditCardService;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Agustin on 2/10/2017.
 */

public class InstallmentPresenter extends BasePresenter<InstallmentMvpView> {

    public void getInstallments(String amount, String paymentMethodId, String issuerId){
        Subscription subscription = CreditCardService.getInstance().getInstallments(amount, paymentMethodId, issuerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RestHttpObserver<ResponseJsonArray>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(ResponseJsonArray response) {

                        Type type = new com.google.gson.reflect.TypeToken<List<Installment>>(){}.getType();
                        List<Installment> installmentList = (List<Installment>) new Gson().fromJson(response.jsonArray, type);

                        if(installmentList.isEmpty()){
                            getMvpView().onInstallmentEmptyList();
                        } else {
                            getInstallment(installmentList);
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
                        Log.e("Get Installments","No internet connection");
                        getMvpView().onNoInternetConnection();
                    }
                });
        compositeSubscription.add(subscription);
    }

    public void getInstallment(List<Installment> installmentList){

        Observable.from(installmentList).first()
                .map(new Func1<Installment, Installment>() {
                    @Override
                    public Installment call(Installment installment) {
                        return installment;
                    }
                }).subscribe(new Observer<Installment>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Installment installment) {
                        getMvpView().onInstallmentFinished(installment.getCostsList(), installment.getIssuer());
                    }
                });
    }
}
