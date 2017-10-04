package com.creditcard.android.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Agustin on 2/10/2017.
 */

public class Installment {

    @SerializedName("payment_method_id")
    private String paymentMethodId;

    @SerializedName("payment_type_id")
    private String paymentTypeId;

    @SerializedName("issuer")
    private Issuer issuer;

    @SerializedName("payer_costs")
    private List<Costs> costsList;


    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Issuer getIssuer() {
        return issuer;
    }

    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
    }

    public List<Costs> getCostsList() {
        return costsList;
    }

    public void setCostsList(List<Costs> costsList) {
        this.costsList = costsList;
    }
}
