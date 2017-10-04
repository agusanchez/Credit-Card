package com.creditcard.android.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.creditcard.android.model.Bank;
import com.creditcard.android.mvp.presenter.BankPresenter;
import com.creditcard.android.mvp.views.BankMvpView;
import com.creditcard.android.ui.adapter.BankAdapter;
import com.creditcard.android.ui.holder.BankViewHolder;
import com.creditcard.android.ui.interfaces.BankCallback;
import com.creditcard.android.ui.session.SessionManager;
import com.creditcard.android.ui.util.Utils;

import java.util.List;

/**
 * Created by Agustin on 1/10/2017.
 */

public class BankSelectionFragment extends BaseListFragment<BankCallback> implements BankMvpView, BankViewHolder.eventCallback {

    private BankPresenter presenter;
    private static String EMPTY_BANK_ID = "";
    private static String EMPTY_BANK_NAME = "";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new BankPresenter();
        presenter.attachMvpView(this);
        presenter.getBanks(SessionManager.getPaymentMethodId(getActivity()));
        showProgress();
    }

    @Override
    public void onBankFinished(List<Bank> bankList) {
        hideProgress();
        setAdapter(new BankAdapter(bankList, getContext(), BankSelectionFragment.this));
    }

    @Override
    public void onEmptyBankList() {
        hideProgress();
        SessionManager.setBankId(getActivity(), EMPTY_BANK_ID);
        SessionManager.setBankName(getActivity(), EMPTY_BANK_NAME);
        callbacks.onEmptyBanks();
    }

    @Override
    public Context getMvpContext() {
        return getActivity();
    }

    @Override
    public void onError(Throwable throwable) {
        hideProgress();
    }

    @Override
    public void onErrorCode(String message) {
        hideProgress();
        Utils.showToast(getContext(), message);
    }

    @Override
    public void onNoInternetConnection() {
        hideProgress();
    }

    @Override
    public void onBankContainerPressed(Bank bank) {
        SessionManager.setBankId(getActivity(), bank.getId());
        SessionManager.setBankName(getActivity(), bank.getName());
        callbacks.onBankCompleted();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dettachMvpView();
    }
}
