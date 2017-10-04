package com.creditcard.android.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creditcard.android.R;
import com.creditcard.android.ui.util.Utils;

public abstract class BaseFragment<T> extends Fragment {

    protected T callbacks;
    protected ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = Utils.getProgressDialog(getAppCompatActivity(), R.string.progress_loading);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callbacks = (T) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement callback interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.callbacks = null;
        hideProgress();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        inflateViews(view);
        return view;
    }


    protected abstract int getLayoutId();

    protected AppCompatActivity getAppCompatActivity() {
        return (AppCompatActivity) getActivity();
    }

    protected abstract void inflateViews(View mainView);

    protected void showProgress(){
        if(progressDialog != null && !progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    protected void hideProgress(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
