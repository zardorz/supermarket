package br.com.bmgsistemas.supermarket.partial;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.content.ContextCompat;
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
import br.com.bmgsistemas.supermarket.enums.eStatementOperationType;
import br.com.bmgsistemas.supermarket.enums.eStatementType;
import br.com.bmgsistemas.supermarket.models.Account;
import br.com.bmgsistemas.supermarket.models.Statement;

public class CardLastTransactions {
    private String name;
    private Activity activity;
    private  LinearLayout parent;

    public CardLastTransactions(Activity activity, LinearLayout parent  ){
        this.name = "Ultimas Transações" ;
        this.activity = activity;
        this.parent = parent;

        List<Statement> statements = new ArrayList<Statement>();

        Statement statement ;

        statement = new Statement();
        statement.setAmount(15.36);
        statement.setDescription("Almoço");
        statement.setCategoryName("Alimentação");
        statement.setSubCategoryName("Trabalho");
        statement.setDate("2018-11-15T15:28:35-0800");
        statement.setOperationType(eStatementOperationType.DEBIT);
        statements.add(statement);

        statement = new Statement();
        statement.setAmount(4.25);
        statement.setDescription("Onibus");
        statement.setCategoryName("Transporte");
        statement.setSubCategoryName("Trabalho");
        statement.setDate("2018-11-14T15:25:35-0800");
        statement.setOperationType(eStatementOperationType.DEBIT);
        statements.add(statement);

        statement = new Statement();
        statement.setAmount(387.16);
        statement.setDescription("Serviço");
        statement.setCategoryName("Serviços");
        statement.setSubCategoryName("Extras");
        statement.setDate("2018-11-13T15:23:35-0800");
        statement.setOperationType(eStatementOperationType.CREDIT);
        statements.add(statement);

        CreateCard(statements);
    }

    public void CreateCard( List<Statement> statements ){


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


        for (int i=0; i< statements.size(); i++) {
            // Inclui as linhas
            Statement statement = statements.get(i);

            // Container da tabela de linhas (duas colunas x duas linhas)
            View item_table_two_columns_double_line = inflaterChild.inflate(R.layout.item_table_two_columns_double_line, null);

            // Seta o icone do container
            ImageView iv_card_line_icon =  item_table_two_columns_double_line.findViewById(R.id.iv_card_line_icon);
//            Drawable background = iv_card_line_icon.getBackground();
//            Drawable image=(Drawable)activity.getResources().getDrawable(R.drawable.circle_solid);
//            iv_card_line_icon.setBackground(image);
            iv_card_line_icon.setImageResource(R.drawable.circle_solid);
            iv_card_line_icon.getLayoutParams().width = getPixels(30);
            iv_card_line_icon.getLayoutParams().height = getPixels(30);

            //get drawable from image button
            GradientDrawable drawable = (GradientDrawable) iv_card_line_icon.getDrawable();

            //set color as integer
            //can use Color.parseColor(color) if color is a string

            if(statement.getOperationType() == eStatementOperationType.CREDIT)
                drawable.setColor(ContextCompat.getColor(activity, R.color.green_900));

            if(statement.getOperationType() == eStatementOperationType.DEBIT)
                drawable.setColor(ContextCompat.getColor(activity, R.color.red ));


            setMargins(iv_card_line_icon,0,2,5,0);


            // Seta o texto da celula 01x01
            TextView  tv_column_left_line_01 =  item_table_two_columns_double_line.findViewById(R.id.tv_column_left_line_01);
            tv_column_left_line_01.setText(statement.getDescription());

            // Seta o texto da celula 01x02
            TextView  tv_column_right_line_01 =  item_table_two_columns_double_line.findViewById(R.id.tv_column_right_line_01);
            tv_column_right_line_01.setText(statement.getDateDayMonth());

            // Seta o texto e tamanho da celula 02x01
            TextView  tv_column_left_line_02 =  item_table_two_columns_double_line.findViewById(R.id.tv_column_left_line_02);
            tv_column_left_line_02.setText(statement.getCategoryName() + "/" + statement.getSubCategoryName());
            tv_column_left_line_02.setTextSize(TypedValue.COMPLEX_UNIT_DIP,12);

            // Seta o texto e tamanho da celula 02x02
            TextView  tv_column_right_line_02 =  item_table_two_columns_double_line.findViewById(R.id.tv_column_right_line_02);
            tv_column_right_line_02.setText(statement.getAmountFormated());
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


