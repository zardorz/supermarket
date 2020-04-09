package br.com.bmgsistemas.supermarket.ui.statement;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.bmgsistemas.supermarket.R;

public class SectionHeaderStatementHistoryView  extends RecyclerView.ViewHolder {

    public TextView tvStatementsTitle;
    public TextView tvStatementsTitleAmount;

    public SectionHeaderStatementHistoryView(View view) {
        super(view);

        tvStatementsTitle = (TextView) view.findViewById(R.id.tvStatementsTitle);
        tvStatementsTitleAmount = (TextView) view.findViewById(R.id.tvStatementsTitleAmount );
    }
}