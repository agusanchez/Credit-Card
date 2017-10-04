package com.creditcard.android.ui.util;

/**
 * Created by Agustin on 2/10/2017.
 */

public class Urls {

//  Parameters
    public static final String AMOUNT = "amount";
    public static final String PAYMENT_METHOD_ID = "payment_method_id";
    public static final String INSTALLMENTS = "installments";
    public static final String ISSUER_ID = "issuer.id";
    public static final String PAYMENT_METHODS = "payment_methods";
    public static final String CARD_ISSUES = "card_issuers";

//  Urls
    public static final String URL_GET_BANKS = PAYMENT_METHODS + "/" + CARD_ISSUES;
    public static final String URL_GET_INSTALLMENTS = PAYMENT_METHODS + "/" + INSTALLMENTS;
}
