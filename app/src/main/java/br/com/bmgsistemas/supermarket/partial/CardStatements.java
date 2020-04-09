package br.com.bmgsistemas.supermarket.partial;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.models.Account;
import br.com.bmgsistemas.supermarket.models.Statement;

public class CardStatements {

    private String name;
    private Activity activity;
    private  LinearLayout parent;

    public CardStatements(Activity activity, LinearLayout parent ) {
        this.name = "Contas";

        this.activity = activity;
        this.parent = parent;
    }

    public void CreateCard ( List<Statement> statements){

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


}


