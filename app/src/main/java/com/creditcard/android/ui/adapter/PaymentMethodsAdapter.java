package com.creditcard.android.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.creditcard.android.R;
import com.creditcard.android.model.CreditCard;
import com.creditcard.android.ui.fragment.PaymentMethodsFragment;
import com.creditcard.android.ui.holder.PaymentMethodsViewHolder;

import java.util.List;

/**
 * Created by Agustin on 1/10/2017.
 */

public class PaymentMethodsAdapter extends RecyclerView.Adapter<PaymentMethodsViewHolder> {

    private List<CreditCard> creditCardList;
    private Context context;
    private PaymentMethodsViewHolder.eventCallback callback;

    public PaymentMethodsAdapter(Context context, List<CreditCard> creditCardList, PaymentMethodsFragment paymentMethodsFragment) {
        this.creditCardList = creditCardList;
        this.context = context;
        this.callback = paymentMethodsFragment;
    }

    @Override
    public PaymentMethodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PaymentMethodsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false), callback);
    }

    @Override
    public void onBindViewHolder(PaymentMethodsViewHolder holder, int position) {
        holder.setContent(creditCardList.get(position), context);
    }

    @Override
    public int getItemCount() {
        return creditCardList.size();
    }
}
