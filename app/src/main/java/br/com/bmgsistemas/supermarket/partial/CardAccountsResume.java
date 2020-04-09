package br.com.bmgsistemas.supermarket.partial;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.enums.eAccountType;

public class CardAccountsResume {


    private View viewChildLayout;
    private String name;
    private eAccountType accountType;
    private Activity activity;


    public CardAccountsResume(String name, eAccountType accountType, Activity activity, LinearLayout parent){
        this.activity = activity;
        this.name = name;
        this.accountType = accountType;

        LayoutInflater inflaterChild = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewChildLayout = inflaterChild.inflate(R.layout.card_account_resume, null);


        ImageView img = viewChildLayout.findViewById(R.id.iv_card_header_icon);
        img.setImageResource(eAccountType.getIcon(this.accountType));


        TextView  tv_card_Title = viewChildLayout.findViewById(R.id.tv_card_header_Title);
        tv_card_Title.setText(this.name);

        TextView  tv_card_SubTitle = viewChildLayout.findViewById(R.id.tv_card_header_SubTitle);
        tv_card_SubTitle.setText(eAccountType.getDescription(this.accountType));

        ConstraintLayout ll_item_column_balance_initial = viewChildLayout.findViewById(R.id.ll_item_column_balance_initial);
        TextView tv_column_left_balance_initial = ll_item_column_balance_initial.findViewById(R.id.tv_column_left);
        TextView tv_column_right_balance_initial = ll_item_column_balance_initial.findViewById(R.id.tv_column_right);
        tv_column_left_balance_initial.setText("Saldo Inicial");
        tv_column_right_balance_initial.setText("R$ 9.999.999.99");

        ConstraintLayout ll_item_column_credits = viewChildLayout.findViewById(R.id.ll_item_column_credits);
        TextView tv_column_left_credits = ll_item_column_credits.findViewById(R.id.tv_column_left);
        TextView tv_column_right_credits = ll_item_column_credits.findViewById(R.id.tv_column_right);
        tv_column_left_credits.setText("Créditos");
        tv_column_right_credits.setText("R$ 9.999.999.99");

        ConstraintLayout ll_item_column_debits = viewChildLayout.findViewById(R.id.ll_item_column_debits);
        TextView tv_column_left_debits = ll_item_column_debits.findViewById(R.id.tv_column_left);
        TextView tv_column_right_debits = ll_item_column_debits.findViewById(R.id.tv_column_right);
        tv_column_left_debits.setText("Debitos");
        tv_column_right_debits.setText("R$ 9.999.999.99");

        ConstraintLayout ll_item_column_balance_actual = viewChildLayout.findViewById(R.id.ll_item_column_balance_actual);
        TextView tv_column_left_balance_actual = ll_item_column_balance_actual.findViewById(R.id.tv_column_left);
        TextView tv_column_right_balance_actual = ll_item_column_balance_actual.findViewById(R.id.tv_column_right);
        tv_column_left_balance_actual.setText("Saldo Atual");
        tv_column_right_balance_actual.setText("R$ 9.999.999.99");

        ConstraintLayout ll_item_column_balance_forecast = viewChildLayout.findViewById(R.id.ll_item_column_balance_forecast);
        TextView tv_column_left_balance_forecast = ll_item_column_balance_forecast.findViewById(R.id.tv_column_left);
        TextView tv_column_right_balance_forecast = ll_item_column_balance_forecast.findViewById(R.id.tv_column_right);
        tv_column_left_balance_forecast.setText("Saldo previsto");
        tv_column_right_balance_forecast.setText("R$ 9.999.999.99");

        parent.addView(viewChildLayout);

        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams)viewChildLayout.getLayoutParams();
//        lp.topMargin = getPixels(10);
//        lp.leftMargin = getPixels(10);
//        lp.rightMargin = getPixels(10);
        lp.bottomMargin = getPixels(10);

        viewChildLayout.setLayoutParams(lp);

    }

    public View getCardAccountsResumeView() {

        return viewChildLayout;
    }

    public String getName() {

        return this.name;
    }

    private void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    private int getPixels(int dps){
        final float scale = this.activity.getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);

        return pixels;
    }

//    @Override
//    protected void onAttachedToWindow() {
//        super.onAttachedToWindow();
//        assert getLayoutParams() != null;
//    }

}


