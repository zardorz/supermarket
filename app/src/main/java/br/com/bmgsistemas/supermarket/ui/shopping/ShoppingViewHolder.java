package br.com.bmgsistemas.supermarket.ui.shopping;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.enums.eShoppingStatus;
import br.com.bmgsistemas.supermarket.interfaces.OnCustomEventListener;

public class ShoppingViewHolder extends RecyclerView.ViewHolder {

    private TextView edshoppingName;
    private TextView edShoppingStatus;
    private TextView edShoppingDate;
    private TextView edShoppingQtdItens;
    private TextView edShoppingTotalAmount;
    private TextView edShoppingLocal;

    private ImageView imgShoppingStatusOpen;
    private ImageView imgShoppingStatusPaid;
//    private ImageView imgShoppingStatusCanceled;

    private Context context;

    private OnCustomEventListener mListener;


    public void setShoppingName(String name) {
        edshoppingName.setText(name);
    }

    public void setShoppingStatus(eShoppingStatus status) {

        imgShoppingStatusOpen.setVisibility(View.GONE);
        imgShoppingStatusPaid.setVisibility(View.GONE);
//        imgShoppingStatusCanceled.setVisibility(View.GONE);

        switch (status) {
            case OPEN:
                imgShoppingStatusOpen.setVisibility(View.VISIBLE);
                break;

            case PAID:
                imgShoppingStatusPaid.setVisibility(View.VISIBLE);
                break;

//         case CANCELED:
//             imgShoppingStatusCanceled.setVisibility(View.VISIBLE);
//             break;

        }

//        Drawable mDrawable = ContextCompat.getDrawable(context, R.drawable.rectangle_rounded_solid);
//        mDrawable.setColorFilter(new PorterDuffColorFilter(0xffff00, PorterDuff.Mode.MULTIPLY));

        edShoppingStatus.setText(eShoppingStatus.getDescription(status));
    }

    public void setShoppingDate(String date) {
        edShoppingDate.setText(date);
    }

    public void setShoppingQtdItens(String qtdItens) {
        edShoppingQtdItens.setText(qtdItens);
    }

    public void setShoppingTotalAmount(String totalAmount) {
        edShoppingTotalAmount.setText(totalAmount);
    }

    public void setShoppingLocal(String local) {
        edShoppingLocal.setText(local);
    }


    //Context context,
    public ShoppingViewHolder( View itemView, OnCustomEventListener listener) {
        super(itemView);

        this.context = itemView.getContext();

        edshoppingName = itemView.findViewById(R.id.edShoppingName);
        edShoppingStatus = itemView.findViewById(R.id.edShoppingStatus);
        edShoppingDate = itemView.findViewById(R.id.edShoppingDate);
        edShoppingQtdItens = itemView.findViewById(R.id.edShoppingQtdItens);
        edShoppingTotalAmount = itemView.findViewById(R.id.edShoppingTotalAmount);
        edShoppingLocal = itemView.findViewById(R.id.edShoppingLocal );

        imgShoppingStatusOpen = itemView.findViewById(R.id.imgShoppingStatusOpen);
        imgShoppingStatusPaid = itemView.findViewById(R.id.imgShoppingStatusPaid);
//        imgShoppingStatusCanceled = itemView.findViewById(R.id.imgShoppingStatusCanceled);
    }

    //Context context,
    public ShoppingViewHolder(View itemView) {
        super(itemView);

        this.context = itemView.getContext();

        edshoppingName = itemView.findViewById(R.id.edShoppingName);
        edShoppingStatus = itemView.findViewById(R.id.edShoppingStatus);
        edShoppingDate = itemView.findViewById(R.id.edShoppingDate);
        edShoppingQtdItens = itemView.findViewById(R.id.edShoppingQtdItens);
        edShoppingTotalAmount = itemView.findViewById(R.id.edShoppingTotalAmount);
        edShoppingLocal = itemView.findViewById(R.id.edShoppingLocal );

        imgShoppingStatusOpen = itemView.findViewById(R.id.imgShoppingStatusOpen);
        imgShoppingStatusPaid = itemView.findViewById(R.id.imgShoppingStatusPaid);
//        imgShoppingStatusCanceled = itemView.findViewById(R.id.imgShoppingStatusCanceled);

    }

}
