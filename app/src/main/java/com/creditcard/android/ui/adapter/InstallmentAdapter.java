package com.creditcard.android.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.creditcard.android.R;
import com.creditcard.android.model.Costs;
import com.creditcard.android.model.Issuer;
import com.creditcard.android.ui.holder.InstallmentViewHolder;

import java.util.List;

/**
 * Created by Agustin on 2/10/2017.
 */

public class InstallmentAdapter extends RecyclerView.Adapter<InstallmentViewHolder> {

    private List<Costs> costsList;
    private Issuer issuer;
    private Context context;
    private InstallmentViewHolder.eventCallback eventCallback;

    public InstallmentAdapter(List<Costs> costsList, Issuer issuer, Context context, InstallmentViewHolder.eventCallback eventCallback) {
        this.costsList = costsList;
        this.issuer = issuer;
        this.context = context;
        this.eventCallback = eventCallback;
    }

    @Override
    public InstallmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InstallmentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false), eventCallback);
    }

    @Override
    public void onBindViewHolder(InstallmentViewHolder holder, int position) {
        holder.setContent(costsList.get(position), context, issuer);
    }

    @Override
    public int getItemCount() {
        return costsList.size();
    }
}
