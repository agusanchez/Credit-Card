package com.creditcard.android.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Agustin on 2/10/2017.
 */

public class Costs {

    @SerializedName("recommended_message")
    private String recommendedMessage;

    public String getRecommendedMessage() {
        return recommendedMessage;
    }

    public void setRecommendedMessage(String recommendedMessage) {
        this.recommendedMessage = recommendedMessage;
    }
}
