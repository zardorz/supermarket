package br.com.bmgsistemas.supermarket.ui.cart;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.enums.eProductListItemClick;
import br.com.bmgsistemas.supermarket.interfaces.OnCustomEventListener;
import br.com.bmgsistemas.supermarket.models.CartItem;
import br.com.bmgsistemas.supermarket.models.Product;
import br.com.bmgsistemas.supermarket.ui.product.EANDecoderActivity;
import br.com.bmgsistemas.supermarket.ui.product.ProductComplexListAdapter;
import br.com.bmgsistemas.supermarket.ui.product.ProductViewHolder;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemViewHolder>{
//    implements View.OnClickListener{


//    private static final String TAG = ProductComplexListAdapter.class.getSimpleName();

    private OnCustomEventListener mListener;
    private Context context;
    private List<CartItem> cartItens;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public ViewHolder(View v) {
            super(v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int itemPosition = getLayoutPosition();

                }

            });
        }
    }

    public CartItemAdapter(Context context, List<CartItem> cartItens) {
        this.context = context;
        this.cartItens = cartItens;
    }


    @Override
    public CartItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_list, parent, false);

        CartItemViewHolder vh = new CartItemViewHolder(view , mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(final CartItemViewHolder holder, int position) {
        CartItem cartItem = cartItens.get(position);

        holder.setCartItem(cartItem);

//        holder.productName.setText(cartItensObject.getProductName());
//        holder.productQtd.setText(cartItensObject.getProductQtd().toString() );
//        holder.productAmountValue.setText(cartItensObject.getProductAmountFormated());
//        holder.productAmountValueTotal.setText(cartItensObject.getProductTotalAmountFormated() );


//        int itemPosition = holder.getLayoutPosition();

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //listener.onItemClick(item);
//
////                if (mListener != null)
////                    mListener.onEventAll(eProductListItemClick.PRODUCT_DETAIL.getNumericType(), itemPosition);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return cartItens.size();
    }

    public static int getResourceId(Context context, String pVariableName, String pResourcename, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        }
    }

    public void setOnCustomEventListener(OnCustomEventListener listener) {
        this.mListener = listener;
    }

}
