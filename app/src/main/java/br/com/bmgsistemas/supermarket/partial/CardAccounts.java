package br.com.bmgsistemas.supermarket.partial;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.bmgsistemas.supermarket.MainActivity;
import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.enums.eAccountType;
import br.com.bmgsistemas.supermarket.models.Account;

public class CardAccounts {


//    private View parent;
    private String name;
    private Activity activity;
    private  LinearLayout parent;

    public CardAccounts(Activity activity, LinearLayout parent ){
        this.name = "Contas" ;

        this.activity = activity;
        this.parent = parent;

        List<Account> accounts = new ArrayList<Account>();

        Account account ;

        account = new Account();
        account.setName("Itau Silvio");
        account.setBalance(99999999.99f);
        account.setBalanceForecast(99999999.99f);
        account.setTypeID(eAccountType.CHECKING_ACCOUNT.getNumericType());

        accounts.add(account);

        account = new Account();
        account.setName("Itau Rosane");
        account.setBalance(99999999.99f);
        account.setBalanceForecast(99999999.99f);
        account.setTypeID(eAccountType.CHECKING_ACCOUNT.getNumericType());

        accounts.add(account);

        account = new Account();
        account.setName("Itau Silvio Poupança");
        account.setBalance(99999999.99f);
        account.setBalanceForecast(99999999.99f);
        account.setTypeID(eAccountType.SAVE.getNumericType());

        accounts.add(account);

        account = new Account();
        account.setName("Dinheiro");
        account.setBalance(99999999.99f);
        account.setBalanceForecast(99999999.99f);
        account.setTypeID(eAccountType.WALLET.getNumericType());

        accounts.add(account);


        account = new Account();
        account.setName("Visa Itau Silvio");
        account.setBalance(99999999.99f);
        account.setBalanceForecast(99999999.99f);
        account.setTypeID(eAccountType.CHECKING_ACCOUNT.getNumericType());

        accounts.add(account);

        CreateCard(accounts);
    }

    public void CreateCard ( List<Account> accounts){

        LayoutInflater inflaterChild = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Cria card
        View card_general_view = inflaterChild.inflate(R.layout.card_accounts, null);

        // Inclui card no container
        parent.addView(card_general_view);

        // Define margem padrao para o card
        setMargins(card_general_view, 10,0,10,10);

        //// Configura o Header do card

        // Oculta a imagem do titulo
        ImageView img = card_general_view.findViewById(R.id.iv_card_header_icon);
        img.setVisibility(View.GONE);

        // Seta o nome do card
        TextView tv_card_Title = card_general_view.findViewById(R.id.tv_card_header_Title);
        tv_card_Title.setText(this.name);

        // Seta as margens do titulo do card
        setMargins(tv_card_Title, 10,0,0,0);

        // Seta o tamanho e estilo do texto
        tv_card_Title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
        tv_card_Title.setTypeface(null, Typeface.BOLD);

        // Oculta o subtitulo do card
        TextView  tv_card_SubTitle = card_general_view.findViewById(R.id.tv_card_header_SubTitle);
        tv_card_SubTitle.setVisibility(View.GONE);

        
        // Cria o container das Linhas do card
        LinearLayout ll_items_column_lines_container = card_general_view.findViewById(R.id.ll_items_column_lines_container);


        for (int i=0; i<accounts.size(); i++) {
            // Inclui as linhas
            Account account = accounts.get(i);

            // Container da tabela de linhas (duas colunas x duas linhas)
            View item_table_two_columns_double_line = inflaterChild.inflate(R.layout.item_table_two_columns_double_line, null);

            // Seta o icone do container
            ImageView iv_card_line_icon =  item_table_two_columns_double_line.findViewById(R.id.iv_card_line_icon);
            iv_card_line_icon.setImageResource(eAccountType.getIcon(eAccountType.fromInteger(account.getTypeID())));

            // Seta o texto da celula 01x01
            TextView  tv_column_left_line_01 =  item_table_two_columns_double_line.findViewById(R.id.tv_column_left_line_01);
            tv_column_left_line_01.setText(account.getName());

            // Seta o texto da celula 01x02
            TextView  tv_column_right_line_01 =  item_table_two_columns_double_line.findViewById(R.id.tv_column_right_line_01);
            tv_column_right_line_01.setText(account.getBalanceFormated());

            // Seta o texto e tamanho da celula 02x01
            TextView  tv_column_left_line_02 =  item_table_two_columns_double_line.findViewById(R.id.tv_column_left_line_02);
            tv_column_left_line_02.setText("Previsto");
            tv_column_left_line_02.setTextSize(TypedValue.COMPLEX_UNIT_DIP,12);

            // Seta o texto e tamanho da celula 02x02
            TextView  tv_column_right_line_02 =  item_table_two_columns_double_line.findViewById(R.id.tv_column_right_line_02);
            tv_column_right_line_02.setText(account.getBalanceForecastFormated());
            tv_column_right_line_02.setTextSize(TypedValue.COMPLEX_UNIT_DIP,12);

            // Inclui o Container da tabela no container das linhas
            ll_items_column_lines_container.addView(item_table_two_columns_double_line);

        }

    }


    public String getName() {

        return this.name;
    }

    private void setMargins (View v, int left, int top, int right, int bottom) {

        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams)v.getLayoutParams();
        lp.topMargin = getPixels(top);
        lp.leftMargin = getPixels(left);
        lp.rightMargin = getPixels(right);
        lp.bottomMargin = getPixels(bottom);

        v.setLayoutParams(lp);

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


