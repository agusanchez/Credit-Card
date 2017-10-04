package com.creditcard.android.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.creditcard.android.R;
import com.creditcard.android.model.Bank;
import com.creditcard.android.ui.fragment.BankSelectionFragment;
import com.creditcard.android.ui.holder.BankViewHolder;

import java.util.List;

/**
 * Created by Agustin on 2/10/2017.
 */

public class BankAdapter extends RecyclerView.Adapter<BankViewHolder> {

    private List<Bank> bankList;
    private Context context;
    private BankViewHolder.eventCallback eventCallback;

    public BankAdapter(List<Bank> bankList, Context context, BankSelectionFragment bankSelectionFragment) {
        this.bankList = bankList;
        this.context = context;
        this.eventCallback = bankSelectionFragment;
    }

    @Override
    public BankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BankViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false),eventCallback);
    }

    @Override
    public void onBindViewHolder(BankViewHolder holder, int position) {
        holder.setContent(bankList.get(position), context);
    }

    @Override
    public int getItemCount() {
        return bankList.size();
    }
}
