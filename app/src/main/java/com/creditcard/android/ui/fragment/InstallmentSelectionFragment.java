package com.creditcard.android.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.creditcard.android.model.Costs;
import com.creditcard.android.model.Issuer;
import com.creditcard.android.mvp.presenter.InstallmentPresenter;
import com.creditcard.android.mvp.views.InstallmentMvpView;
import com.creditcard.android.ui.adapter.InstallmentAdapter;
import com.creditcard.android.ui.holder.InstallmentViewHolder;
import com.creditcard.android.ui.interfaces.InstallmentCallback;
import com.creditcard.android.ui.session.SessionManager;
import com.creditcard.android.ui.util.Utils;

import java.util.List;

/**
 * Created by Agustin on 2/10/2017.
 */

public class InstallmentSelectionFragment extends BaseListFragment<InstallmentCallback> implements InstallmentMvpView, InstallmentViewHolder.eventCallback {

    private InstallmentPresenter presenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new InstallmentPresenter();
        presenter.attachMvpView(this);
        presenter.getInstallments(SessionManager.getUserAmount(getActivity()), SessionManager.getPaymentMethodId(getActivity()), SessionManager.getBankId(getActivity()));
        showProgress();
    }

    @Override
    public void onInstallmentFinished(List<Costs> costsList, Issuer issuer) {
        hideProgress();
        setAdapter(new InstallmentAdapter(costsList, issuer, getContext(), InstallmentSelectionFragment.this));
    }

    @Override
    public void onInstallmentEmptyList() {
        hideProgress();
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
    public void onInstallmentContainerPressed(String recommendedMessage) {
        SessionManager.setRecommendedMessage(getContext(), recommendedMessage);
        callbacks.onInstallmentSelected();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dettachMvpView();
    }
}
