package com.creditcard.android.mvp.views;

/**
 * Created by Agustin on 1/10/2017.
 */

public interface EnterAmountMvpView extends MvpView {
    void onEmptyField();
    void onIncorrectAmount();
    void onCorrectAmount();
    void onIncorrectEditTextEntry();
}
