package br.com.bmgsistemas.supermarket.partial;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.enums.eAccountType;
import br.com.bmgsistemas.supermarket.models.Account;

public class CardResumeMonth {

    private String name;
    private Activity activity;
    private LinearLayout parent;


    public CardResumeMonth(Activity activity, LinearLayout parent ){
        this.activity = activity;
        this.name = "Saldo Atual";
        this.parent = parent;

        List<AbstractMap.SimpleEntry<String,String>> itens = new ArrayList<AbstractMap.SimpleEntry<String,String>>() ;

        AbstractMap.SimpleEntry<String,String> item ;

        item = new AbstractMap.SimpleEntry<String,String>("Contas", "9.999.999,99");
        itens.add(item);

        item = new AbstractMap.SimpleEntry<String,String>("Cartões", "9.999.999,99");
        itens.add(item);

        CreateCard(itens);
    }

    private void CreateCard(List<AbstractMap.SimpleEntry<String,String>> itens){


        LayoutInflater inflaterChild = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Cria card
        View card_general_view = inflaterChild.inflate(R.layout.card_resume_month, null);

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


        for(Map.Entry<String, String> item : itens) {
            /// Inclui as linhas


            // Container da tabela de linhas (duas colunas x uma linha)
            View item_table_two_columns_one_line = inflaterChild.inflate(R.layout.item_table_two_columns, null);

//            // Seta o icone do container
//            ImageView iv_card_line_icon =  item_table_two_columns_one_line.findViewById(R.id.iv_card_line_icon);
//            iv_card_line_icon.setVisibility(View.GONE);
//            //iv_card_line_icon.setImageResource(eAccountType.getIcon(eAccountType.fromInteger(account.getTypeID())));


            View  separator_line =  item_table_two_columns_one_line.findViewById(R.id.separator_line);
            separator_line.setVisibility(View.GONE);

            // Seta o texto da celula 01x01
            TextView  tv_column_left =  item_table_two_columns_one_line.findViewById(R.id.tv_column_left);
            tv_column_left.setText(item.getKey());

            // Seta o texto da celula 01x02
            TextView  tv_column_right =  item_table_two_columns_one_line.findViewById(R.id.tv_column_right);
            tv_column_right.setText(item.getValue());

            // Inclui o Container da tabela no container das linhas
            ll_items_column_lines_container.addView(item_table_two_columns_one_line);

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


