package com.creditcard.android.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.creditcard.android.R;
import com.creditcard.android.mvp.presenter.EnterAmountPresenter;
import com.creditcard.android.mvp.views.EnterAmountMvpView;
import com.creditcard.android.ui.interfaces.EnterAmountCallback;
import com.creditcard.android.ui.session.SessionManager;
import com.creditcard.android.ui.util.Utils;

/**
 * Created by Agustin on 1/10/2017.
 */

public class EnterAmountFragment extends BaseFragment<EnterAmountCallback> implements EnterAmountMvpView {

    private EditText edtAmount;
    private Button btnConfirmAmount;
    private EnterAmountPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_enter_amount;
    }

    @Override
    protected void inflateViews(View mainView) {
        edtAmount = (EditText) mainView.findViewById(R.id.edtAmount);
        btnConfirmAmount = (Button) mainView.findViewById(R.id.btnConfirmAmount);

        setListeners();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new EnterAmountPresenter();
        presenter.attachMvpView(this);
    }

    private void setListeners() {

        btnConfirmAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.validateAmountEntry(edtAmount.getText().toString());
            }
        });

        edtAmount.addTextChangedListener(new TextWatcher(){
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                presenter.validateEditTextEntry(edtAmount.getText().toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }
            public void afterTextChanged(Editable s){

            }
        });
    }

    @Override
    public void onEmptyField() {
        Utils.showToast(getContext(), R.string.enter_amount_empty_field);
    }

    @Override
    public void onIncorrectAmount() {
        Utils.showToast(getContext(), R.string.enter_amount_value_greater_than_zero);
    }

    @Override
    public void onCorrectAmount() {
        SessionManager.setUserAmount(getActivity(), edtAmount.getText().toString());
        edtAmount.getText().clear();
        callbacks.onEnterAmountCompleted();
    }

    @Override
    public void onIncorrectEditTextEntry() {
        edtAmount.getText().clear();
    }

    @Override
    public Context getMvpContext() {
        return getActivity();
    }

    @Override
    public void onError(Throwable throwable) {
    }

    @Override
    public void onErrorCode(String message) {
    }

    @Override
    public void onNoInternetConnection() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dettachMvpView();
    }
}
