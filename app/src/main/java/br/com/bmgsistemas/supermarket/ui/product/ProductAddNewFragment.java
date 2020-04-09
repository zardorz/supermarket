package br.com.bmgsistemas.supermarket.ui.product;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
import br.com.bmgsistemas.supermarket.ui.cart.ProductCartDetailFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductAddNewFragment extends Fragment {

    private View view;
    private TextView edBarCode;
    private Spinner spnProductBrand;
    private Spinner spnManufacturer;
    private Spinner spnProductCategory;

    private String productName;
    private String barCode;

    private ShoppingEntity shopping;

    private ProductBrandEntity productBrandSelected;
    private ManufacturerEntity manufacturerSelected;
    private ProductCategoryEntity productCategorySelected;

    private MutableLiveData<List<ProductBrandEntity>> productsBrand;
    private MutableLiveData<List<ManufacturerEntity>> manufactures;
    private MutableLiveData<List<ProductCategoryEntity>> productsCategory;

    public ProductAddNewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        MainActivity.setFragmentTitle("Cadastrar Produto");
        MainActivity.showBackIcon();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product_add_new, container, false);

        edBarCode = view.findViewById(R.id.edBarCode);

        spnProductBrand = view.findViewById(R.id.spnProductBrand);
        spnManufacturer = view.findViewById(R.id.spnManufacturer);
        spnProductCategory = view.findViewById(R.id.spnProductCategory);

        barCode = getArguments().getString("BarCode");

        shopping = (ShoppingEntity) getArguments().getSerializable("ShoppingEntity");

        edBarCode.setText(barCode);

        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isValidFields())
                    return;

                doSaveProduct();
            }
        });

        productsBrand = new MutableLiveData<List<ProductBrandEntity>>();
        getProductsBrand().observe(this, localProductsBrand -> {

            ArrayAdapter<ProductBrandEntity> spinnerArrayAdapter = new ArrayAdapter<ProductBrandEntity>(
                    ((MainActivity) getActivity()).getSupportActionBar().getThemedContext(),
                    R.layout.spinner_item,
                    localProductsBrand);


            ProductBrandEntity emptyItem = new ProductBrandEntity();
            emptyItem.Name = "Selecione";

            spinnerArrayAdapter.insert(emptyItem, 0);

            // The drop down view
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            spnProductBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    productBrandSelected = productsBrand.getValue().get(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });


            spnProductBrand.setAdapter(spinnerArrayAdapter);
            spnProductBrand.setSelection(0);

        });

        manufactures = new MutableLiveData<List<ManufacturerEntity>>();
        getManufactures().observe(this, manufactures1 -> {

            ArrayAdapter<ManufacturerEntity> spinnerArrayAdapter = new ArrayAdapter<ManufacturerEntity>(
                    ((MainActivity) getActivity()).getSupportActionBar().getThemedContext(),
                    R.layout.spinner_item,
                    manufactures1);


            ManufacturerEntity emptyItem = new ManufacturerEntity();
            emptyItem.Name = "Selecione";

            spinnerArrayAdapter.insert(emptyItem, 0);

            // The drop down view
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            spnManufacturer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    manufacturerSelected = manufactures.getValue().get(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            spnManufacturer.setAdapter(spinnerArrayAdapter);
            spnManufacturer.setSelection(0);
        });

        productsCategory = new MutableLiveData<List<ProductCategoryEntity>>();
        getProductsCategory().observe(this, productsCategory1 -> {

            ArrayAdapter<ProductCategoryEntity> spinnerArrayAdapter = new ArrayAdapter<ProductCategoryEntity>(
                    ((MainActivity) getActivity()).getSupportActionBar().getThemedContext(),
                    R.layout.spinner_item,
                    productsCategory1);

            ProductCategoryEntity emptyItem = new ProductCategoryEntity();
            emptyItem.Name = "Selecione";

            spinnerArrayAdapter.insert(emptyItem, 0);

            // The drop down view
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            spnProductCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    productCategorySelected = productsCategory.getValue().get(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            spnProductCategory.setAdapter(spinnerArrayAdapter);
            spnProductCategory.setSelection(0);
        });

        fillDB();

        return view;
    }

    public LiveData<List<ProductBrandEntity>> getProductsBrand() {

        return productsBrand;
    }

    public LiveData<List<ManufacturerEntity>> getManufactures() {

        return manufactures;
    }

    public LiveData<List<ProductCategoryEntity>> getProductsCategory() {

        return productsCategory;
    }

    private void fillDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<ProductBrandEntity> productBrandsEntity = GlobalState.getDB().productBrandDao().getAll();
                productsBrand.postValue(productBrandsEntity);

                List<ManufacturerEntity> manufacturesEntity = GlobalState.getDB().manufacturerDao().getAll();
                manufactures.postValue(manufacturesEntity);

                List<ProductCategoryEntity> productsCategoryEntity = GlobalState.getDB().productCategoryDao().getAll();
                productsCategory.postValue(productsCategoryEntity);

            }
        }).start();
    }

    // Validacao basica dos campos de tela
    private boolean isValidFields() {
        boolean validFields = true;

        productName = ((EditText) view.findViewById(R.id.edProductName)).getText().toString().trim();

        TextInputLayout edProductNameView = view.findViewById(R.id.edProductNameView);

        edProductNameView.setError(null);

        if (TextUtils.isEmpty(productName)) {
            edProductNameView.setError("Campo Obrigatório!");
            validFields = false;
        }

//        if(productBrandSelected.Name.equals("Selecione")){
//            ArrayAdapter<ProductBrandEntity> adapter = (ArrayAdapter<ProductBrandEntity>)spnProductBrand.getAdapter();
//            View view = spnProductBrand.getSelectedView();
//            adapter.setError(view, getActivity().getString(R.string.error_message));
//            validFields = false;
//        }
//        if(manufacturerSelected.Name.equals("Selecione")){
//
//        }
//        if(productCategorySelected.Name.equals("Selecione")){
//
//        }

        return validFields;
    }

    private void doSaveProduct() {
        new Thread(new Runnable() {
            @Override
            public void run() {

//              Verifica se barcode já existe
                ProductEntity product = GlobalState.getDB().productDao().findByBarCode(barCode);

                if (product ==  null) {
//                  Não Existe? cadastra
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.Name = productName;
                    productEntity.BarCode = barCode;

                    if (productBrandSelected != null)
                        productEntity.ProductBrandID = productBrandSelected.ID;

                    if (manufacturerSelected != null)
                        productEntity.ManufacturerID = manufacturerSelected.ID;

                    if (productCategorySelected != null)
                        productEntity.ProductCategoryID = productCategorySelected.ID;

                    GlobalState.getDB().productDao().insert(productEntity);

                    productEntity = GlobalState.getDB().productDao().findByBarCode(barCode);


//                  Cadastrou?
                    if (shopping != null) {

//                      É cart? Vai para o detalhe
                        Fragment fragment = new ProductCartDetailFragment();

                        Bundle bundle = new Bundle();

                        bundle.putSerializable("ProductEntity", productEntity);
                        bundle.putSerializable("ShoppingEntity", shopping);

                        fragment.setArguments(bundle);

                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, fragment)
                                .commit();

                        return;
                    }

//                  Não é? Volta para a lista de produtos
                    Fragment fragment = new ProductFragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("BarCode", barCode);
                    fragment.setArguments(bundle);

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();

                    return;
                }

//              Deu erro? Fecha a tela
                getActivity().getSupportFragmentManager().popBackStack();;

            }
        }).start();

    }
}
