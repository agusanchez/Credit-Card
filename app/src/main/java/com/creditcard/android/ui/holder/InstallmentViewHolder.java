package com.creditcard.android.ui.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.creditcard.android.R;
import com.creditcard.android.model.Costs;
import com.creditcard.android.model.Issuer;

/**
 * Created by Agustin on 2/10/2017.
 */

public class InstallmentViewHolder extends RecyclerView.ViewHolder {

    private RelativeLayout rtlContainer;
    private ImageView imgBank;
    private TextView txtBank;
    private InstallmentViewHolder.eventCallback callback;

    public InstallmentViewHolder(View itemView, InstallmentViewHolder.eventCallback eventCallback) {
        super(itemView);
        this.callback = eventCallback;
        rtlContainer = (RelativeLayout) itemView.findViewById(R.id.rtlContainer);
        imgBank = (ImageView) itemView.findViewById(R.id.imgSource);
        txtBank = (TextView) itemView.findViewById(R.id.txtName);
    }

    public void setContent(final Costs costs, Context context, Issuer issuer) {

        txtBank.setText(costs.getRecommendedMessage());

        Glide.with(context)
                .load(issuer.getThumbnail())
                .placeholder(R.drawable.ic_credit_card)
                .error(R.drawable.ic_credit_card)
                .into(imgBank);

        rtlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onInstallmentContainerPressed(costs.getRecommendedMessage());
            }
        });
    }

    public interface eventCallback {
        void onInstallmentContainerPressed(String recommendedMessage);
    }
}
