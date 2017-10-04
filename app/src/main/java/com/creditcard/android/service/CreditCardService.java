package com.creditcard.android.service;

import com.creditcard.android.BuildConfig;
import com.creditcard.android.model.ResponseJsonArray;
import com.creditcard.android.service.deserealizer.ResponseGsonDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Agustin on 30/9/2017.
 */

public class CreditCardService {

    private static final int TIMEOUT_MILLIS = 30000;
    private static final TimeUnit TIMEOUT_UNIT = TimeUnit.MILLISECONDS;
    private static CreditCardService instance;
    private Retrofit retrofit;
    private CreditCardApi creditCardApi;


    public static CreditCardService getInstance() {
        if (instance == null)
            instance = new CreditCardService();
        return instance;
    }


    private CreditCardService() {
        if (retrofit == null || creditCardApi == null) {

            Type typeUser = new com.google.gson.reflect.TypeToken<ResponseJsonArray>(){}.getType();
            Gson gson = new GsonBuilder().registerTypeAdapter(typeUser, new ResponseGsonDeserializer()).create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(createOkHttpClientInterceptor())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            creditCardApi = retrofit.create(CreditCardApi.class);
        }
    }

    private OkHttpClient createOkHttpClientInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().connectTimeout(TIMEOUT_MILLIS, TIMEOUT_UNIT).readTimeout(TIMEOUT_MILLIS, TIMEOUT_UNIT).addInterceptor(interceptor).addNetworkInterceptor(new HeaderInterceptor()).build();
    }

    public Observable<ResponseJsonArray> getPaymentMethods() {
        return creditCardApi.getPaymentMethods();
    }

    public Observable<ResponseJsonArray> getBanks(String paymentMethodId) {
        return creditCardApi.getBanks(paymentMethodId);
    }

    public Observable<ResponseJsonArray> getInstallments(String amount, String paymentMethodId, String issuerId) {
        return creditCardApi.getInstallments(amount, paymentMethodId, issuerId);
    }
}
