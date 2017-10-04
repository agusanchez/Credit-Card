package com.creditcard.android.model;

import com.google.gson.annotations.SerializedName;

public class ResponseError {

    @SerializedName("message")
    private String message;

    private int errorCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
