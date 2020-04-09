package br.com.bmgsistemas.supermarket.helpers;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyMask
        implements TextWatcher{

    final EditText campo;
    private Locale ptBr = new Locale("pt", "BR");
    private boolean isEditing = false;
    private NumberFormat nf = NumberFormat.getCurrencyInstance(ptBr);

    public CurrencyMask(EditText campo) {
        super();
        this.campo = campo;

        this.campo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)});
    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int after) {
        if (!isEditing ) {
            isEditing = true;
            String str = s.toString();
            String digits = s.toString().replaceAll("\\D", "");
            NumberFormat nf = NumberFormat.getInstance(ptBr);

            nf.setMinimumFractionDigits(2);


            try {
                String formatted = nf.format(Double.parseDouble(digits) / 100);
                campo.setText(formatted);
                campo.setSelection(formatted.length());
            } catch (Exception e) {
                str = "";
            }

            isEditing = false;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Não utilizado
    }

    @Override
    public synchronized void afterTextChanged(Editable s) {

    }
}

