package br.com.bmgsistemas.supermarket.ui.product;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import br.com.bmgsistemas.supermarket.MainActivity;
import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.core.GlobalState;
import br.com.bmgsistemas.supermarket.entity.CartEntity;
import br.com.bmgsistemas.supermarket.entity.ProductEntity;
import br.com.bmgsistemas.supermarket.entity.ShoppingEntity;
import br.com.bmgsistemas.supermarket.helpers.CurrencyMask;
import br.com.bmgsistemas.supermarket.helpers.MaskEditUtil;
import br.com.bmgsistemas.supermarket.ui.cart.CartFragment;
import br.com.bmgsistemas.supermarket.ui.shopping.ShoppingFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends Fragment {

    private View view;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout drawerLayout;
    private EditText edProductAmountTotal;
    private EditText edProductQtd;
    private EditText edProductAmount;

    private ProductEntity product;
    private ShoppingEntity shopping;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        MainActivity.setFragmentTitle("Detalhe do Produto");
        MainActivity.showBackIcon();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        product = (ProductEntity) getArguments().getSerializable("ProductEntity");

        shopping = (ShoppingEntity) getArguments().getSerializable("ShoppingEntity");

        ((TextView) view.findViewById(R.id.edProductName)).setText(product.Name);


//        edProductAmountTotal = view.findViewById(R.id.edProductAmountTotal);
//
//        edProductQtd = view.findViewById(R.id.edProductQtd);
//        edProductQtd.addTextChangedListener(MaskEditUtil.mask(edProductQtd, MaskEditUtil.FORMAT_NUMBER));
//
//        edProductQtd.addTextChangedListener(new TextWatcher() {
//
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!s.equals("")) {
//                    //do your work here
//                }
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            public void afterTextChanged(Editable s) {
//                String qtdString = edProductQtd.getText().toString();
//
//                if (qtdString.isEmpty()) {
//                    edProductAmountTotal.setText("0,00");
//
//                    return;
//                }
//
//                int qtd = Integer.parseInt(qtdString);
//
//                String amountString = edProductAmount.getText().toString();
//
//                if (amountString.isEmpty()) {
//                    edProductAmountTotal.setText("0,00");
//
//                    return;
//                }
//
//
//                float amount = Float.parseFloat(amountString.replace(".", "").replace(",", "."));
//                float total = amount * qtd;
//
//                Locale ptBr = new Locale("pt", "BR");
//                String amountFormated = NumberFormat.getCurrencyInstance(ptBr).format(total);
//                amountFormated = amountFormated.replace("R$", "");
//
//                edProductAmountTotal.setText(amountFormated);
//            }
//        });
//        edProductAmount = view.findViewById(R.id.edProductAmount);
//        edProductAmount.addTextChangedListener(new CurrencyMask(edProductAmount));
//        edProductAmount.addTextChangedListener(new TextWatcher() {
//
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!s.equals("")) {
//                    //do your work here
//                }
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            public void afterTextChanged(Editable s) {
//                String qtdString = edProductQtd.getText().toString();
//
//                if (qtdString.isEmpty()) {
//                    edProductAmountTotal.setText("0,00");
//
//                    return;
//                }
//
//                int qtd = Integer.parseInt(qtdString);
//
//                String amountString = edProductAmount.getText().toString();
//
//                if (amountString.isEmpty()) {
//                    edProductAmountTotal.setText("0,00");
//
//                    return;
//                }
//
//                float amount = Float.parseFloat(amountString.replace(".", "").replace(",", "."));
//                float total = amount * qtd;
//
//                Locale ptBr = new Locale("pt", "BR");
//                String amountFormated = NumberFormat.getCurrencyInstance(ptBr).format(total);
//                amountFormated = amountFormated.replace("R$", "");
//
//                edProductAmountTotal.setText(amountFormated);
//            }
//        });


//        productQtd              = ((EditText)view.findViewById(R.id.edProductQtd)).getText();
////        productAmountCardTotal  = Float.parseFloat(((EditText)view.findViewById(R.id.edProductAmountCardTotal)).getText();
////        productAmountBudget     = view.findViewById(R.id.edProductAmountBudget);


//        view.findViewById(R.id.imgProductAddCart).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!isValidFields())
//                    return;
//
//                doSaveProductToCart();
//            }
//        });

        return view;
    }

//    // Validacao basica dos campos de tela
//    private boolean isValidFields() {
//        boolean validFields = true;
//
//        String productQtd = edProductQtd.getText().toString();
//        String productAmount = edProductAmount.getText().toString();
//
//
//        TextInputLayout edProductQtdView = view.findViewById(R.id.edProductQtdView);
//        TextInputLayout edProductAmountView = view.findViewById(R.id.edProductAmountView);
//
//
//        edProductAmountView.setError(null);
//        edProductQtdView.setError(null);
//
//        if (TextUtils.isEmpty(productQtd)) {
//            edProductQtdView.setError("Campo Obrigatório!");
//            validFields = false;
//        } else if (Integer.parseInt(productQtd) <= 0) {
//            edProductQtdView.setError("Campo Obrigatório!");
//            validFields = false;
//        }
//
//        if (TextUtils.isEmpty(productAmount)) {
//            edProductAmountView.setError("Campo Obrigatório!");
//            validFields = false;
//        } else if (Float.parseFloat(productAmount.replaceAll("\\.", "").replaceAll("\\,", ".")) <= 0) {
//            edProductAmountView.setError("Campo Obrigatório!");
//            validFields = false;
//        }
//
//        return validFields;
//    }
//
//    private void doSaveProductToCart() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                Date date = new Date();
//
//                Integer qtd = Integer.parseInt(edProductQtd.getText().toString());
//                float amount = Float.parseFloat(edProductAmount.getText().toString().replaceAll("\\.", "").replaceAll("\\,", "."));
//                float total = amount * qtd;
//
//                float amountTotal = Float.parseFloat(edProductAmountTotal.getText().toString().replaceAll("\\.", "").replaceAll("\\,", "."));
//
//                CartEntity cart = new CartEntity();
//                cart.ID = UUID.randomUUID().toString();
//                //cart.StoreID = productBrandSelected.ID;
////                cart.BuyDate = sdf.format(date);
//                cart.ShoppingID = shopping.ID;
//                cart.ProductBarCode = product.BarCode;
//                cart.Qtd = qtd;
//                cart.Amount = amount;
//                cart.TotalAmount = total;
//
//                GlobalState.getDB().cartDao().insert(cart);
//
//                //ShoppingFragment fragment = new ShoppingFragment();
//                CartFragment fragment = new CartFragment();
//
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("ShoppingEntity", shopping);
//
//                fragment.setArguments(bundle);
//
//                getFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container, fragment)
//                        .commit();
//
//            }
//        }).start();
//
//    }
}
