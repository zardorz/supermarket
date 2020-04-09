package br.com.bmgsistemas.supermarket.ui.shopping;


import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import br.com.bmgsistemas.supermarket.MainActivity;
import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.core.GlobalState;
import br.com.bmgsistemas.supermarket.entity.ManufacturerEntity;
import br.com.bmgsistemas.supermarket.entity.ProductBrandEntity;
import br.com.bmgsistemas.supermarket.entity.ProductCategoryEntity;
import br.com.bmgsistemas.supermarket.entity.ProductEntity;
import br.com.bmgsistemas.supermarket.entity.ShoppingEntity;
import br.com.bmgsistemas.supermarket.entity.StoreEntity;
import br.com.bmgsistemas.supermarket.interfaces.OnCustomEventListener;
import br.com.bmgsistemas.supermarket.models.Product;
import br.com.bmgsistemas.supermarket.models.Shopping;
import br.com.bmgsistemas.supermarket.ui.cart.CartFragment;
import br.com.bmgsistemas.supermarket.ui.product.ProductAdapter;
import br.com.bmgsistemas.supermarket.ui.product.ProductComplexListFragment;
import br.com.bmgsistemas.supermarket.ui.product.ProductDetailFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingAddNewFragment extends Fragment {

    private View view;

    private Spinner spnStore;
    private TextView edShoppingName;
    private StoreEntity storeSelected;
//    private MutableLiveData<List<ShoppingEntity>> shoppings;
    private MutableLiveData<List<StoreEntity>> stores;

    public ShoppingAddNewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();

//        if (getActivity() instanceof AppCompatActivity) {
//            AppCompatActivity activity = ((AppCompatActivity) getActivity());
//            if (activity.getSupportActionBar() != null) {
//                activity.getSupportActionBar().setTitle("Cadastrar Nova Compra");
//
//                activity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_24dp);
//                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//            }
//        }
        MainActivity.setFragmentTitle("Cadastrar Nova Compra");
        MainActivity.showBackIcon();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_shopping_add_new, container, false);

        edShoppingName = view.findViewById(R.id.spnProductBrand);
        spnStore = view.findViewById(R.id.spnStore);

        stores = new MutableLiveData<List<StoreEntity>>();
        getStores().observe(this, localStore -> {
            ArrayAdapter<StoreEntity> spinnerArrayAdapter = new ArrayAdapter<StoreEntity>(
                    ((MainActivity) getActivity()).getSupportActionBar().getThemedContext(),
                    R.layout.spinner_item,
                    localStore);

            // The drop down view
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            spnStore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    storeSelected = stores.getValue().get(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            spnStore.setAdapter(spinnerArrayAdapter);
            spnStore.setSelection(0);

        });

        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isValidFields())
                    return;

                doSaveShopping();
            }
        });

//        shoppings = new MutableLiveData<List<ShoppingEntity>>();
//        getShoppings().observe(this, localShopping -> {
//            // Update UI.
//            List<Shopping> featuredShoppings = new ArrayList<Shopping>();
//
//            for (ShoppingEntity item : localShopping) {
//                featuredShoppings.add(new Shopping(item.Name, item.BuyDate ));
//            }
//
//            RecyclerView bestRecyclerView = (RecyclerView) view.findViewById(R.id.product_list);
//
//            bestRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//            bestRecyclerView.setHasFixedSize(true);
//            bestRecyclerView.setNestedScrollingEnabled(false);
//
//            ShoppingAdapter mAdapter = new ShoppingAdapter(GlobalState.getContext(), featuredShoppings);
//            bestRecyclerView.setAdapter(mAdapter);
//
//            mAdapter.setOnCustomEventListener(new OnCustomEventListener() {
//                @Override
//                public void onEvent() {
//
//                }
//
//                @Override
//                public void onEventValue(int value) {
//
//                }
//
//                @Override
//                public void onEventPosition(int position) {
//
//                }
//
//                @Override
//                public void onEventAll(int value, int position) {
//                    ShoppingEntity item = shoppings.getValue().get(position);
//
////                            ProductDetailFragment fragment = new ProductDetailFragment();
////
////                            Bundle bundle = new Bundle();
////                            bundle.putSerializable("ProductEntity", item);
////                            fragment.setArguments(bundle);
////
////                            getFragmentManager()
////                                    .beginTransaction()
////                                    .replace(R.id.fragment_container, fragment)
////                                    .addToBackStack(null)
////                                    .commit();
//                }
//
//            });
//
//        });

        fillDB();

        InputMethodManager mImm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mImm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        mImm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);


        return view;
    }


    // Validacao basica dos campos de tela
    private boolean isValidFields() {
        boolean validFields = true;

        String shoppingName = ((EditText) view.findViewById(R.id.edShoppingName)).getText().toString().trim();

        TextInputLayout edShoppingNameView = view.findViewById(R.id.edShoppingNameView);

        edShoppingNameView.setError(null);

        if (TextUtils.isEmpty(shoppingName)) {
            edShoppingNameView.setError("Campo Obrigatório!");
            validFields = false;
        }

        return validFields;
    }

//    public LiveData<List<ShoppingEntity>> getShoppings() {
//
//        return shoppings;
//    }

    public LiveData<List<StoreEntity>> getStores() {

        return stores;
    }

    private void fillDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                List<ShoppingEntity> shoppingEntity = GlobalState.getDB().shoppingDao().getAll();
//                shoppings.postValue(shoppingEntity);

                List<StoreEntity> StoreEntity = GlobalState.getDB().storeDao().getAll();
                stores.postValue(StoreEntity);

            }
        }).start();
    }

    private void doSaveShopping() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                String shoppingName = ((EditText) view.findViewById(R.id.edShoppingName)).getText().toString().trim();

                Date date = new Date();
                String strDateFormat = "yyyy-MM-dd'T'hh:mm:ss a";
                DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
                String buyDateFormated= dateFormat.format(date);

                ShoppingEntity shoppingEntity = new ShoppingEntity();
                shoppingEntity.ID = UUID.randomUUID().toString();
                shoppingEntity.Name = shoppingName;
                shoppingEntity.BuyDate = buyDateFormated;
                shoppingEntity.StoreID = storeSelected.ID;

                GlobalState.getDB().shoppingDao().insert(shoppingEntity);

                ShoppingEntity newShoppingEntity = GlobalState.getDB().shoppingDao().findEntityByID(shoppingEntity.ID);

                // Isso vai dar problema, pois o back vai tentar vir para ca
                CartFragment fragment = new CartFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable("ShoppingEntity", newShoppingEntity);
                fragment.setArguments(bundle);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();

            }
        }).start();

    }
}
