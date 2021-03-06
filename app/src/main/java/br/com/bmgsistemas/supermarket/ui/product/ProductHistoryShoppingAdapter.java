package br.com.bmgsistemas.supermarket.ui.product;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.enums.eProductListItemClick;
import br.com.bmgsistemas.supermarket.interfaces.OnCustomEventListener;
import br.com.bmgsistemas.supermarket.models.Product;

public class ProductHistoryShoppingAdapter extends RecyclerView.Adapter<ProductHistoryShoppingViewHolder>{
//    implements View.OnClickListener{


    private static final String TAG = ProductComplexListAdapter.class.getSimpleName();

    private OnCustomEventListener mListener;
    private Context context;
    private List<Product> productList;

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

    public ProductHistoryShoppingAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }


    @Override
    public ProductHistoryShoppingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_hitory_item_list, parent, false);

        ProductHistoryShoppingViewHolder vh = new ProductHistoryShoppingViewHolder(view); //, mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(final  ProductHistoryShoppingViewHolder holder, int position) {
        Product productObject = productList.get(position);
        holder.productName.setText(productObject.getName());

        int itemPosition = holder.getLayoutPosition();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listener.onItemClick(item);

                if (mListener != null)
                    mListener.onEventAll(eProductListItemClick.PRODUCT_DETAIL.getNumericType(), itemPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
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
