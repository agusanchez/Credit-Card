package com.creditcard.android.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.creditcard.android.R;
import com.creditcard.android.ui.fragment.BankSelectionFragment;
import com.creditcard.android.ui.fragment.EnterAmountFragment;
import com.creditcard.android.ui.fragment.InstallmentSelectionFragment;
import com.creditcard.android.ui.fragment.PaymentMethodsFragment;
import com.creditcard.android.ui.interfaces.BankCallback;
import com.creditcard.android.ui.interfaces.EnterAmountCallback;
import com.creditcard.android.ui.interfaces.InstallmentCallback;
import com.creditcard.android.ui.interfaces.PaymentMethodsCallback;
import com.creditcard.android.ui.session.SessionManager;
import com.creditcard.android.ui.util.Utils;

public class MainActivity extends AppCompatActivity implements EnterAmountCallback, PaymentMethodsCallback, BankCallback, InstallmentCallback {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        addFragment(Fragment.instantiate(this, EnterAmountFragment.class.getName()));
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void addFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.add(R.id.frame_container, fragment);
        transaction.commit();
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onEnterAmountCompleted() {
        replaceFragment(Fragment.instantiate(this, PaymentMethodsFragment.class.getName()));
    }

    @Override
    public void onPaymentMethodSelected() {
        replaceFragment(Fragment.instantiate(this, BankSelectionFragment.class.getName()));
    }

    @Override
    public void onBankCompleted() {
        replaceFragment(Fragment.instantiate(this, InstallmentSelectionFragment.class.getName()));
    }

    @Override
    public void onEmptyBanks() {
        popFragmentFromBackStack();
        replaceFragment(Fragment.instantiate(this, InstallmentSelectionFragment.class.getName()));
    }

    @Override
    public void onInstallmentSelected() {
        popAllFragments();
        Utils.showAlertDialog(this, getUserInfo());
    }

    private void popAllFragments() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void popFragmentFromBackStack(){
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }

    private String getUserInfo(){

        String amount = getResources().getString(R.string.message_amount) + " " + SessionManager.getUserAmount(this);
        String paymentMethod = getResources().getString(R.string.message_payment_method) + " " + SessionManager.getPaymentMethodName(this);
        String bank = getResources().getString(R.string.message_bank) + " " + SessionManager.getBankName(this);
        String recommendedMessage = getResources().getString(R.string.message_recommended) + " " + SessionManager.getRecommendedMessage(this);

        return amount + "\n" + paymentMethod + "\n" + bank + "\n" + recommendedMessage;
    }
}
