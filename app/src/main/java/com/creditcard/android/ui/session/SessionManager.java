package com.creditcard.android.ui.session;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Agustin on 2/10/2017.
 */

public class SessionManager {

    public static final String USER_AMOUNT = "amount";
    public static final String USER_PAYMENT_METHOD_ID = "payment_method";
    public static final String USER_PAYMENT_METHOD_NAME = "payment_method_name";
    public static final String USER_BANK_ID = "bank_id";
    public static final String USER_BANK_NAME = "bank_name";
    public static final String USER_RECOMMENDED_MESSAGE = "recommended_message";
    public static final String SHARED_KEY = "credit_card";


    public static final void setUserAmount(Context context, String amount) {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_AMOUNT, amount);
        editor.commit();
    }

    public static final String getUserAmount(Context context){
        SharedPreferences prefs = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        return prefs.getString(USER_AMOUNT, "");
    }

    public static final void setPaymentMethodId(Context context, String paymentMethodId) {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_PAYMENT_METHOD_ID, paymentMethodId);
        editor.commit();
    }

    public static final String getPaymentMethodId(Context context){
        SharedPreferences prefs = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        return prefs.getString(USER_PAYMENT_METHOD_ID, "");
    }

    public static final void setPaymentMethodName(Context context, String paymentMethodName) {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_PAYMENT_METHOD_NAME, paymentMethodName);
        editor.commit();
    }

    public static final String getPaymentMethodName(Context context){
        SharedPreferences prefs = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        return prefs.getString(USER_PAYMENT_METHOD_NAME, "");
    }

    public static final void setBankId(Context context, String bankId) {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_BANK_ID, bankId);
        editor.commit();
    }

    public static final String getBankId(Context context){
        SharedPreferences prefs = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        return prefs.getString(USER_BANK_ID, "");
    }

    public static final void setBankName(Context context, String bankName) {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_BANK_NAME, bankName);
        editor.commit();
    }

    public static final String getBankName(Context context){
        SharedPreferences prefs = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        return prefs.getString(USER_BANK_NAME, "");
    }

    public static final void setRecommendedMessage(Context context, String recommendedMessage) {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_RECOMMENDED_MESSAGE, recommendedMessage);
        editor.commit();
    }

    public static final String getRecommendedMessage(Context context){
        SharedPreferences prefs = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        return prefs.getString(USER_RECOMMENDED_MESSAGE, "");
    }
}
