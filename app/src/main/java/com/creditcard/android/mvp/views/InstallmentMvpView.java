package com.creditcard.android.mvp.views;

import com.creditcard.android.model.Costs;
import com.creditcard.android.model.Issuer;

import java.util.List;

/**
 * Created by Agustin on 2/10/2017.
 */

public interface InstallmentMvpView extends MvpView {

    void onInstallmentFinished(List<Costs> costsList, Issuer issuer);
    void onInstallmentEmptyList();
}
