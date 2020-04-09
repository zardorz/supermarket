package br.com.bmgsistemas.supermarket.ui.statement;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.bmgsistemas.supermarket.R;

public class SectionItemStatementHistoryView extends RecyclerView.ViewHolder {

    public View rootView;
    public TextView tvDateDay;
    public TextView tvDateWeekName;
    public TextView tvItemDescription;
    public TextView tvItemCategoryName;
    public TextView tvItemAmount;
    public TextView tvItemBalanceAmount;

    public SectionItemStatementHistoryView(View view) {
        super(view);

        rootView = view;

        tvDateDay = (TextView) view.findViewById(R.id.tvDateDay);
        tvDateWeekName = (TextView) view.findViewById(R.id.tvDateWeekName);

        tvItemDescription = (TextView) view.findViewById(R.id.tvItemDescription);
        tvItemCategoryName = (TextView) view.findViewById(R.id.tvItemCategoryName);

        tvItemAmount = (TextView) view.findViewById(R.id.tvItemAmount);
        tvItemBalanceAmount = (TextView) view.findViewById(R.id.tvItemBalanceAmount);
    }
}
