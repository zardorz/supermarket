package br.com.bmgsistemas.supermarket.ui.product;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.enums.eProductListItemClick;
import br.com.bmgsistemas.supermarket.interfaces.OnCustomEventListener;
import br.com.bmgsistemas.supermarket.models.Product;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    public TextView productName;
    private OnCustomEventListener mListener;

    public ProductViewHolder(View itemView, OnCustomEventListener listener) {
        super(itemView);

        productName = (TextView) itemView.findViewById(R.id.edProductName);
    }

    public ProductViewHolder(View itemView) {
        super(itemView);

        productName = (TextView) itemView.findViewById(R.id.edProductName);


    }

}
