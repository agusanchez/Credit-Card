package com.creditcard.android.mvp.views;

import com.creditcard.android.model.Bank;

import java.util.List;

/**
 * Created by Agustin on 2/10/2017.
 */

public interface BankMvpView extends MvpView {

    void onBankFinished(List<Bank> bankList);
    void onEmptyBankList();
}
