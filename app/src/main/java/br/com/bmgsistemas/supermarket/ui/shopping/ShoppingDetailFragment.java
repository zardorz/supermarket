package br.com.bmgsistemas.supermarket.ui.shopping;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.entity.ProductEntity;
import br.com.bmgsistemas.supermarket.entity.ShoppingEntity;
import br.com.bmgsistemas.supermarket.helpers.MaskEditUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingDetailFragment extends Fragment {

    private View view;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout drawerLayout;
//    private EditText edProductAmountTotal;
//    private EditText edProductQtd;
//    private EditText edProductAmount;


    private ShoppingEntity item;

    public ShoppingDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();

        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = ((AppCompatActivity) getActivity());
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setTitle("Detalhe da Compra (Deveria ser o carrinho)");

                activity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_24dp);
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            }
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_shopping_detail, container, false);

        item = (ShoppingEntity) getArguments().getSerializable("ShoppingEntity");

        ((TextView) view.findViewById(R.id.edShoppingName)).setText(item.BuyDate);

//        edProductAmountTotal = view.findViewById(R.id.edProductAmountTotal);
//        edProductQtd = view.findViewById(R.id.edProductQtd);



        return  view;
    }

}
