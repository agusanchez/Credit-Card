package com.creditcard.android.mvp.presenter;

import com.creditcard.android.mvp.views.EnterAmountMvpView;

/**
 * Created by Agustin on 1/10/2017.
 */

public class EnterAmountPresenter extends BasePresenter<EnterAmountMvpView> {


    public void validateAmountEntry(String amount) {

        if(amount.equals("")){
            getMvpView().onEmptyField();
            return;
        }

        if(amount.substring(0, 1).matches("^0")){
            getMvpView().onIncorrectAmount();
            return;
        }

        getMvpView().onCorrectAmount();
    }

    public void validateEditTextEntry(String amount) {

        if (amount.matches("^0") ) {
            getMvpView().onIncorrectEditTextEntry();
        }
    }
}
