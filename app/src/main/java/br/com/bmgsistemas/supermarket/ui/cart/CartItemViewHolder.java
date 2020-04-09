package br.com.bmgsistemas.supermarket.ui.cart;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.enums.eProductListItemClick;
import br.com.bmgsistemas.supermarket.interfaces.OnCustomEventListener;
import br.com.bmgsistemas.supermarket.models.CartItem;

public class CartItemViewHolder extends RecyclerView.ViewHolder {

    private CartItem cartItem;

    private TextView productName;
    private TextView productQtd;
    private TextView productAmountValue;
    private TextView productAmountValueTotal;

//    private ImageView imgProductEdit;
//    private ImageView imgProductDelete;
    private ImageView imgProductDetail;

    private OnCustomEventListener mListener;

    public CartItemViewHolder(View itemView, OnCustomEventListener listener) {
        super(itemView);

        this.mListener = listener;

        productName = (TextView) itemView.findViewById(R.id.edProductName);
        productQtd = (TextView) itemView.findViewById(R.id.edProductQtd);
        productAmountValue = (TextView) itemView.findViewById(R.id.edProductAmountValue);
        productAmountValueTotal = (TextView) itemView.findViewById(R.id.edProductAmountValueTotal);
        imgProductDetail = itemView.findViewById(R.id.imgProductDetail );

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onEventPosition(getAdapterPosition());
            }
        });

//        imgProductDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mListener != null)
//                    mListener.onEventPosition (getAdapterPosition());
//            }
//        });

//        imgProductEdit = itemView.findViewById(R.id.imgProductEdit);
//        imgProductDelete = itemView.findViewById(R.id.imgProductDelete);
//
//        imgProductEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mListener != null)
//                    mListener.onEventAll( eProductListItemClick.EDIT.getNumericType(), getAdapterPosition());
//            }
//        });
//
//        imgProductDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mListener != null)
//                    mListener.onEventAll( eProductListItemClick.DELETE.getNumericType(), getAdapterPosition());
//            }
//        });
    }

//    public CartItemViewHolder(View itemView) {
//        super(itemView);
//
//        productName = (TextView) itemView.findViewById(R.id.edProductName);
//        productQtd = (TextView) itemView.findViewById(R.id.edProductQtd);
//        productAmountValue = (TextView) itemView.findViewById(R.id.edProductAmountValue);
//        productAmountValueTotal = (TextView) itemView.findViewById(R.id.edProductAmountValueTotal);
//        imgProductEdit = itemView.findViewById(R.id.imgProductEdit);
//        imgProductDelete = itemView.findViewById(R.id.imgProductDelete);
//
//        imgProductEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        imgProductDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//    }


    public void setCartItem(CartItem cartItem){
        this.cartItem = cartItem;

        productName.setText(cartItem.getProductName());

        String productQtdFormatted = String.format("%03d", cartItem.getProductQtd());
        productQtd.setText(productQtdFormatted );
        productAmountValue.setText(cartItem.getProductAmountFormated());
        productAmountValueTotal.setText(cartItem.getProductTotalAmountFormated() );

    }

}
