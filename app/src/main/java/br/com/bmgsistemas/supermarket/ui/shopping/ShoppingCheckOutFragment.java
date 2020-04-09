package br.com.bmgsistemas.supermarket.ui.shopping;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.core.AlertDialogCustom;
import br.com.bmgsistemas.supermarket.core.GlobalState;
import br.com.bmgsistemas.supermarket.entity.ShoppingEntity;
import br.com.bmgsistemas.supermarket.enums.eShoppingStatus;
import br.com.bmgsistemas.supermarket.helpers.CurrencyMask;
import br.com.bmgsistemas.supermarket.helpers.MaskEditUtil;
import br.com.bmgsistemas.supermarket.models.CartProduct;
import br.com.bmgsistemas.supermarket.ui.cart.CartFragment;
import br.com.bmgsistemas.supermarket.ui.product.ProductDetailFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingCheckOutFragment extends Fragment {

    private View view;
    private ShoppingEntity shopping;
    private int itensQtd;
    private float amountTotal;
    private float totalDiscount;
    private float amountPaid;

    public ShoppingCheckOutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_shopping_check_out, container, false);

        shopping = (ShoppingEntity) getArguments().getSerializable("ShoppingEntity");
        itensQtd = getArguments().getInt("ItensQtd");
        amountTotal = getArguments().getFloat("AmountTotal");

        TextView edItensQtd = view.findViewById(R.id.edItensQtd);
        edItensQtd.setText(String.valueOf(itensQtd));

        TextView edAmountTotal = view.findViewById(R.id.edAmountTotal);

        Locale ptBr = new Locale("pt", "BR");

        NumberFormat f = NumberFormat.getNumberInstance(ptBr);
        f.setMinimumFractionDigits(2);

        String amountTotalFormated = f.format(amountTotal);

        edAmountTotal.setText(String.valueOf(amountTotalFormated));


        view.findViewById(R.id.imgSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isValidFields())
                    return;

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        ShoppingEntity shoppingEntity = GlobalState.getDB().shoppingDao().findEntityByID(shopping.ID);

                        shoppingEntity.TotalAmount = amountTotal;
                        shoppingEntity.QtdItens = itensQtd;

                        shoppingEntity.TotalDiscount = totalDiscount;
                        shoppingEntity.TotalPaid = amountPaid;

                        shoppingEntity.Status = eShoppingStatus.PAID.getNumericType();

                        GlobalState.getDB().shoppingDao().update(shoppingEntity);

                        Fragment fragment = new ShoppingFragment();

                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, fragment)
                                .commit();


                    }
                }).start();
            }
        });

        view.findViewById(R.id.imgCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new CartFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable("ShoppingEntity", shopping);

                fragment.setArguments(bundle);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();

            }
        });


        EditText edAmountDiscount = view.findViewById(R.id.edAmountDiscount);
        edAmountDiscount.addTextChangedListener(new CurrencyMask(edAmountDiscount));

        EditText edAmountPaid = view.findViewById(R.id.edAmountPaid);
        edAmountPaid.addTextChangedListener(new CurrencyMask(edAmountPaid));

        return view;
    }


    private boolean isValidFields() {
        boolean validFields = true;

        TextView edAmountDiscount = view.findViewById(R.id.edAmountDiscount);
        TextView edAmountPaid = view.findViewById(R.id.edAmountPaid);
        TextView edAmountTotal = view.findViewById(R.id.edAmountTotal);

        TextInputLayout edAmountDiscountView = view.findViewById(R.id.edAmountDiscountView);
        TextInputLayout edAmountPaidView = view.findViewById(R.id.edAmountPaidView);

        edAmountDiscountView.setError(null);
        edAmountPaidView.setError(null);

        if (TextUtils.isEmpty(edAmountDiscount.getText().toString())) {
            edAmountDiscountView.setError("Campo Obrigatório!");
            validFields = false;
        } else if (Float.parseFloat(edAmountDiscount.getText().toString().replace(".", "").replace(",", ".")) < 0) {
            edAmountDiscountView.setError("Valor Inválido!");
            validFields = false;
        }

        if (TextUtils.isEmpty(edAmountPaid.getText().toString())) {
            edAmountPaidView.setError("Campo Obrigatório!");
            validFields = false;
        } else if (Float.parseFloat(edAmountPaid.getText().toString().replace(".", "").replace(",", ".")) < Float.parseFloat(edAmountTotal.getText().toString().replace(".", "").replace(",", "."))) {
            edAmountPaidView.setError("Valor Inválido!");
            validFields = false;
        }

        totalDiscount = Float.parseFloat(edAmountDiscount.getText().toString().replace(".", "").replace(",", "."));
        amountPaid = Float.parseFloat(edAmountPaid.getText().toString().replace(".", "").replace(",", "."));

        return validFields;
    }

}
