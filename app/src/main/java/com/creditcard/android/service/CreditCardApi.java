package com.creditcard.android.service;

import com.creditcard.android.model.ResponseJsonArray;
import com.creditcard.android.ui.util.Urls;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Agustin on 30/9/2017.
 */

public interface CreditCardApi {

    @GET(Urls.PAYMENT_METHODS)
    Observable<ResponseJsonArray> getPaymentMethods();

    @GET(Urls.URL_GET_BANKS)
    Observable<ResponseJsonArray> getBanks(@Query(Urls.PAYMENT_METHOD_ID) String paymentMethodId);

    @GET(Urls.URL_GET_INSTALLMENTS)
    Observable<ResponseJsonArray> getInstallments(@Query(Urls.AMOUNT) String amount, @Query(Urls.PAYMENT_METHOD_ID) String paymentMethodId, @Query(Urls.ISSUER_ID) String issuerId);
}
