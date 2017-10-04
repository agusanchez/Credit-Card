package com.creditcard.android.ui.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.creditcard.android.R;
import com.creditcard.android.model.CreditCard;

/**
 * Created by Agustin on 1/10/2017.
 */

public class PaymentMethodsViewHolder extends RecyclerView.ViewHolder {

    private RelativeLayout rtlContainer;
    private ImageView imgCreditCard;
    private TextView txtCreditCard;
    private eventCallback eventCallback;

    public PaymentMethodsViewHolder(View itemView, eventCallback callback) {
        super(itemView);
        rtlContainer = (RelativeLayout) itemView.findViewById(R.id.rtlContainer);
        imgCreditCard = (ImageView) itemView.findViewById(R.id.imgSource);
        txtCreditCard = (TextView) itemView.findViewById(R.id.txtName);
        this.eventCallback = callback;
    }

    public void setContent(final CreditCard creditCard, Context context) {

        txtCreditCard.setText(creditCard.getName());

        Glide.with(context)
                .load(creditCard.getThumbnail())
                .placeholder(R.drawable.ic_credit_card)
                .error(R.drawable.ic_credit_card)
                .into(imgCreditCard);

        rtlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventCallback.onCreditCardContainerPressed(creditCard);
            }
        });
    }

    public interface eventCallback {
        void onCreditCardContainerPressed(CreditCard creditCard);
    }
}
