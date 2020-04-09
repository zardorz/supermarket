package br.com.bmgsistemas.supermarket.ui.product;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.interfaces.OnCustomEventListener;

public class ProductHistoryShoppingViewHolder extends RecyclerView.ViewHolder {

    public TextView productName;
    private OnCustomEventListener mListener;

    public ProductHistoryShoppingViewHolder(View itemView, OnCustomEventListener listener) {
        super(itemView);

        productName = (TextView) itemView.findViewById(R.id.edProductName);
    }

    public ProductHistoryShoppingViewHolder(View itemView) {
        super(itemView);

        productName = (TextView) itemView.findViewById(R.id.edProductName);


    }

}
