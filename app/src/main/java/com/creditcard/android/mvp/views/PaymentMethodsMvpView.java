package com.creditcard.android.mvp.views;

import com.creditcard.android.model.CreditCard;

import java.util.List;

/**
 * Created by Agustin on 1/10/2017.
 */

public interface PaymentMethodsMvpView extends MvpView {

    void onPaymentMethodsFinished(List<CreditCard> creditCardList);
    void onAmountMissing();
    void onAmountExceeded();
    void onCorrectAmount(CreditCard creditCard);
}
