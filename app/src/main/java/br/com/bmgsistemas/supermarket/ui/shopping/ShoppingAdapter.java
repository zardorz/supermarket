package br.com.bmgsistemas.supermarket.ui.shopping;

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
import br.com.bmgsistemas.supermarket.models.Shopping;
import br.com.bmgsistemas.supermarket.ui.product.ProductComplexListAdapter;
import br.com.bmgsistemas.supermarket.ui.product.ProductViewHolder;


//https://github.com/ajaydewari/FloatingActionButtonMenu
//https://stackoverflow.com/questions/30699302/android-design-support-library-expandable-floating-action-buttonfab-menu
//https://github.com/wangjiegulu/RapidFloatingActionButton

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingViewHolder>{

//    private static final String TAG = ProductComplexListAdapter.class.getSimpleName();

    private OnCustomEventListener mListener;
    private Context context;
    private List<Shopping> shoppingList;

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

    public ShoppingAdapter(Context context, List<Shopping> shoppingList) {
        this.context = context;
        this.shoppingList = shoppingList;
    }


    @Override
    public ShoppingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_item_list, parent, false);

        ShoppingViewHolder vh = new ShoppingViewHolder(view); //, mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ShoppingViewHolder holder, int position) {
        Shopping shoppingObject = shoppingList.get(position);

        //Copiar processo do CartAdapter SILVIO

        holder.setShoppingName(shoppingObject.getName());
        holder.setShoppingStatus(shoppingObject.getStatusToEnum());
        holder.setShoppingDate(shoppingObject.getBuyDateFormated());
        holder.setShoppingQtdItens(String.valueOf(shoppingObject.getQtdItens()));
        //holder.setShoppingTotalAmount (shoppingObject.getTotalPaidFormated());
        holder.setShoppingTotalAmount(shoppingObject.getTotalAmountFormated());
        holder.setShoppingLocal(shoppingObject.getStoreName());

        int itemPosition = holder.getLayoutPosition();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listener.onItemClick(item);

                if (mListener != null)
                    mListener.onEventPosition(itemPosition);
                    //mListener.onEvent(eProductListItemClick.PRODUCT_DETAIL.getNumericType(), itemPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
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
