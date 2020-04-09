package br.com.bmgsistemas.supermarket.ui.statement;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import br.com.bmgsistemas.supermarket.models.Statement;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.bmgsistemas.supermarket.R;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class SectionStatementHistory extends StatelessSection {

    //    String titleSection;
//    Float amountBalance;
//    String amountBalanceFormated;
    List<Statement> list;


    SectionStatementHistory(List<Statement> list) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.section_item_statement_history_view)
//                .headerResourceId(R.layout.section_header_statement_history_view)
//                .footerResourceId(R.layout.section_useraccount_footer)
                .build());

//        this.titleSection = titleSection;
//        this.amountBalance = amountBalance;
//        this.amountBalanceFormated = amountBalanceFormated;
        this.list = list;
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new SectionItemStatementHistoryView(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final SectionItemStatementHistoryView itemStatementHolder = (SectionItemStatementHistoryView) holder;

        Statement item = list.get(position);


        itemStatementHolder.tvDateDay.setText(item.getDateDay());
        itemStatementHolder.tvDateWeekName.setText(item.getDateWeekDay());
        itemStatementHolder.tvItemDescription.setText(item.getDescription());

        String categoryName =  item.getCategoryName();
        String subCategoryName =  item.getSubCategoryName();

        if(!TextUtils.isEmpty(subCategoryName))
            categoryName += ":" + subCategoryName;

        itemStatementHolder.tvItemCategoryName.setText(categoryName);


        Locale ptBr = new Locale("pt", "BR");
        String amount = NumberFormat.getCurrencyInstance(ptBr).format(item.getAmount());

        String balance = NumberFormat.getCurrencyInstance(ptBr).format(item.getBalance());


        itemStatementHolder.tvItemAmount.setText(amount);

        if(item.getAmount() < 0)
            itemStatementHolder.tvItemAmount.setTextColor(Color.parseColor("#ec1022"));
        else
            itemStatementHolder.tvItemAmount.setTextColor(Color.parseColor("#0C4855"));

        itemStatementHolder.tvItemBalanceAmount.setText(balance);

        if(item.getAmount() < 0)
            itemStatementHolder.tvItemBalanceAmount.setTextColor(Color.parseColor("#ec1022"));
        else
            itemStatementHolder.tvItemBalanceAmount.setTextColor(Color.parseColor("#0C4855"));

    }


    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new SectionHeaderStatementHistoryView(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        SectionHeaderStatementHistoryView headerStatementHolder = (SectionHeaderStatementHistoryView) holder;

//        headerStatementHolder.tvStatementsTitle.setText(titleSection);
//
//        headerStatementHolder.tvStatementsTitleAmount.setText(amountBalanceFormated);
//
//        if(amountBalance < 0)
//            headerStatementHolder.tvStatementsTitleAmount.setTextColor(Color.parseColor("#ec1022"));
//        else
//            headerStatementHolder.tvStatementsTitleAmount.setTextColor(Color.parseColor("#0C4855"));
    }

}