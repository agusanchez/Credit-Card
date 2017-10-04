package com.creditcard.android.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Agustin on 1/10/2017.
 */

public class CreditCard {


    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("payment_type_id")
    private String paymentTypeId;

    @SerializedName("secure_thumbnail")
    private String secureThumbnail;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("min_allowed_amount")
    private String minAmount;

    @SerializedName("max_allowed_amount")
    private String maxAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getSecureThumbnail() {
        return secureThumbnail;
    }

    public void setSecureThumbnail(String secureThumbnail) {
        this.secureThumbnail = secureThumbnail;
    }

    public String getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
    }

    public String getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
