package com.creditcard.android.ui.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.creditcard.android.R;
import com.creditcard.android.model.Bank;

/**
 * Created by Agustin on 2/10/2017.
 */

public class BankViewHolder extends RecyclerView.ViewHolder {

    private RelativeLayout rtlContainer;
    private ImageView imgBank;
    private TextView txtBank;
    private BankViewHolder.eventCallback eventCallback;

    public BankViewHolder(View itemView, BankViewHolder.eventCallback eventCallback) {
        super(itemView);
        this.eventCallback = eventCallback;
        rtlContainer = (RelativeLayout) itemView.findViewById(R.id.rtlContainer);
        imgBank = (ImageView) itemView.findViewById(R.id.imgSource);
        txtBank = (TextView) itemView.findViewById(R.id.txtName);
    }

    public void setContent(final Bank bank, Context context) {

        txtBank.setText(bank.getName());

        Glide.with(context)
                .load(bank.getThumbnail())
                .placeholder(R.drawable.ic_bank)
                .error(R.drawable.ic_bank)
                .into(imgBank);


        rtlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventCallback.onBankContainerPressed(bank);
            }
        });
    }

    public interface eventCallback {
        void onBankContainerPressed(Bank bank);
    }
}
