package com.creditcard.android.rx;

import android.util.Log;

import com.creditcard.android.model.ResponseError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.ConnectException;
import java.net.HttpURLConnection;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;


public abstract class RestHttpObserver<T> implements Observer<T> {


    public static final int UNAUTHORIZED = HttpURLConnection.HTTP_UNAUTHORIZED;

    public static final int INTERNAL_SERVER_ERROR = HttpURLConnection.HTTP_INTERNAL_ERROR;

    public static final int NOT_FOUND = HttpURLConnection.HTTP_NOT_FOUND;

    public static final int BAD_REQUEST = HttpURLConnection.HTTP_BAD_REQUEST;

    protected Gson gson = new GsonBuilder().create();

    private int errorCode;

    private String response;

    @Override
    public void onError(Throwable e) {
        Log.e("Credit Card","Server error: " + e.getMessage());

        if(e instanceof HttpException) {

            errorCode = ((HttpException) e).code();

                try {
                    response = ((HttpException) e).response().errorBody().string();

                    ResponseError responseError = gson.fromJson(response, ResponseError.class);

                    if(errorCode == UNAUTHORIZED) {
                        onErrorCode(responseError.getMessage(), errorCode);
                    } else if(errorCode == NOT_FOUND) {
                        onErrorCode(responseError.getMessage(), errorCode);
                    } else if(errorCode == BAD_REQUEST){
                        onErrorCode(responseError.getMessage(), errorCode);
                    } else if(errorCode == INTERNAL_SERVER_ERROR) {
                        onErrorCode(responseError.getMessage(), errorCode);
                    } else {
						onObserverError(e);
                    }

                } catch (Exception e1) {
                    onObserverError(e);
                    e1.printStackTrace();
                }

        } else if(e instanceof ConnectException){
            onNoInternetConnection();
        } else {
            onObserverError(e);
        }
    }

    public abstract void onNoInternetConnection();
    public abstract void onObserverError(Throwable e);
    public abstract void onErrorCode(String message, int errorCode);
}
