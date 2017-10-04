package com.creditcard.android.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creditcard.android.R;
import com.creditcard.android.ui.util.Utils;

/**
 * Created by Agustin on 2/10/2017.
 */

public abstract class BaseListFragment<T> extends Fragment {

    protected T callbacks;
    protected ProgressDialog progressDialog;
    protected RecyclerView recyclerView;

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
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(getActivity()).getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);
    }

    protected AppCompatActivity getAppCompatActivity() {
        return (AppCompatActivity) getActivity();
    }

    protected void setAdapter(RecyclerView.Adapter adapter){
        recyclerView.setAdapter(adapter);
    }

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
