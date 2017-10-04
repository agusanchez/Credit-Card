package com.creditcard.android.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.creditcard.android.R;
import com.creditcard.android.model.CreditCard;
import com.creditcard.android.mvp.presenter.PaymentMethodsPresenter;
import com.creditcard.android.mvp.views.PaymentMethodsMvpView;
import com.creditcard.android.ui.adapter.PaymentMethodsAdapter;
import com.creditcard.android.ui.holder.PaymentMethodsViewHolder;
import com.creditcard.android.ui.interfaces.PaymentMethodsCallback;
import com.creditcard.android.ui.session.SessionManager;
import com.creditcard.android.ui.util.Utils;

import java.util.List;

/**
 * Created by Agustin on 1/10/2017.
 */

public class PaymentMethodsFragment extends BaseListFragment<PaymentMethodsCallback> implements PaymentMethodsMvpView, PaymentMethodsViewHolder.eventCallback {

    private PaymentMethodsPresenter presenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new PaymentMethodsPresenter();
        presenter.attachMvpView(this);
        presenter.getPaymentMethods();
        showProgress();
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
    public void onPaymentMethodsFinished(List<CreditCard> creditCardList) {
        hideProgress();

        if(creditCardList != null){
            setAdapter(new PaymentMethodsAdapter(getContext(), creditCardList, PaymentMethodsFragment.this));
        }
    }

    @Override
    public void onAmountMissing() {
        Utils.showToast(getContext(), getActivity().getResources().getString(R.string.payment_methods_amount_missing));
    }

    @Override
    public void onAmountExceeded() {
        Utils.showToast(getContext(),getActivity().getResources().getString(R.string.payment_methods_amount_exceded));
    }

    @Override
    public void onCorrectAmount(CreditCard creditCard) {
        SessionManager.setPaymentMethodId(getActivity(), creditCard.getId());
        SessionManager.setPaymentMethodName(getActivity(), creditCard.getName());
        callbacks.onPaymentMethodSelected();
    }

    @Override
    public void onCreditCardContainerPressed(CreditCard creditCard) {
        presenter.validateAmount(creditCard, SessionManager.getUserAmount(getActivity()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dettachMvpView();
    }
}
