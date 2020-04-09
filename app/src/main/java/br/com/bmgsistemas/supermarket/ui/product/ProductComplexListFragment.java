package br.com.bmgsistemas.supermarket.ui.product;


import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.bmgsistemas.supermarket.MainActivity;
import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.core.AlertDialogCustom;
import br.com.bmgsistemas.supermarket.core.GlobalState;
import br.com.bmgsistemas.supermarket.dao.ProductDao;
import br.com.bmgsistemas.supermarket.entity.ProductEntity;
import br.com.bmgsistemas.supermarket.interfaces.OnCustomEventListener;
import br.com.bmgsistemas.supermarket.models.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductComplexListFragment extends Fragment {

    private static final int MY_PERMISSION_REQUEST_CAMERA = 0;
    private static final int EAN_REQUEST = 1;  // The request code

    private static Runnable mHandlerAddProduct;
    private static Runnable mHandlerEmptyProduct;

    private String barCode;
    private View view;
    private AlertDialogCustom alert;
    private Activity activity;
    private EditText edProductSeach;
    private ProductDao productDao;
    private MutableLiveData<List<ProductEntity>> products;


    public ProductComplexListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

//        Toolbar toolbar = MainActivity.getToolbar(); // (Toolbar) view.findViewById(R.id.toolbar);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Contas");

//        doLoadFinanceAccounts();

        MainActivity.setFragmentTitle("Produtos");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        alert = new AlertDialogCustom();

        mHandlerAddProduct = new Runnable() {
            public void run() {
                ProductAddNewFragment fragment = new ProductAddNewFragment();

                String barCode = edProductSeach.getText().toString();

                //Limpa para evtar exibir o codigo caso o user faça back
                edProductSeach.setText("");

                Bundle bundle = new Bundle();
                bundle.putString("BarCode", barCode);
                fragment.setArguments(bundle);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        };

        mHandlerEmptyProduct = new Runnable() {
            public void run() {

                //User nao quiz cadastrar.Limpa
                edProductSeach.setText("");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<ProductEntity> productEntities = GlobalState.getDB().productDao().getAll();

                        products.postValue(productEntities);

                    }
                }).start();

            }
        };

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product_complex_list, container, false);


        view.findViewById(R.id.imgScanProduct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(getActivity(), EANDecoderActivity.class);
                    startActivityForResult(intent, EAN_REQUEST);
                } else {
                    requestCameraPermission();
                }
            }
        });

        //ProductDetailFragment

        edProductSeach = view.findViewById(R.id.edProductSeach);


        products = new MutableLiveData<List<ProductEntity>>();
        getProducts().observe(this, products1 -> {
            // Update UI.
            List<Product> featuredProducts = new ArrayList<Product>();

            for (ProductEntity item : products1) {
                featuredProducts.add(new Product(item.BarCode, item.Name, "lap1"));
            }

            RecyclerView bestRecyclerView = (RecyclerView) view.findViewById(R.id.product_list);
//            GridLayoutManager mGrid = new GridLayoutManager(GlobalState.getContext(), 1);

//          bestRecyclerView.setLayoutManager(mGrid);
            bestRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            bestRecyclerView.setHasFixedSize(true);
            bestRecyclerView.setNestedScrollingEnabled(false);

            ProductComplexListAdapter mAdapter = new ProductComplexListAdapter(GlobalState.getContext(), featuredProducts);
            bestRecyclerView.setAdapter(mAdapter);

            mAdapter.setOnCustomEventListener(new OnCustomEventListener() {
                @Override
                public void onEvent() {

                }

                @Override
                public void onEventValue(int value) {

                }

                @Override
                public void onEventPosition(int position) {

                }

                @Override
                public void onEventAll(int value, int position) {
                    ProductEntity item = products.getValue().get(position);

                    switch (value){
                        case 1:
//                            return eProductListItemClick.PRODUCT_SATISFACTION;
                            break;
                        case 2:
//                            return eProductListItemClick.PRODUCT_LASTBUYDATE;
                            break;
                        case 3:
//                            return eProductListItemClick.PRODUCT_MAXPRICE;
                            break;
                        case 4:
//                            return eProductListItemClick.PRODUCT_AVERAGEPRICE;
                            break;
                        case 5:
//                            return eProductListItemClick.PRODUCT_QTDINVENTORY;
                            break;
                        case 6:
//                            return eProductListItemClick.PRODUCT_DETAIL;
                            ProductDetailFragment fragment = new ProductDetailFragment();

                            Bundle bundle = new Bundle();
                            bundle.putSerializable ("ProductEntity", item);
                            fragment.setArguments(bundle);

                            getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, fragment)
                                    .addToBackStack(null)
                                    .commit();
                            break;

                    }
                }

            });

            if (products1.size() == 0) {

//                ViewSwitcher viewSwitcher = view.findViewById(R.id.switcher);
////                View myFirstView = view.findViewById(R.id.product_list);
////                View mySecondView = view.findViewById(R.id.empty_view);
//
//                viewSwitcher.showNext();


                String barCode = edProductSeach.getText().toString();

                if (!barCode.isEmpty()) {

                    //produto nao localizado, cadastrar?
                    alert.showConfirmAction(view.getContext(),
                            "Produto não localizado!",
                            "Deseja cadastrar?",
                            "Sim",
                            "Não",
                            mHandlerAddProduct,
                            mHandlerEmptyProduct);
                }

                return;
            }
        });


        createList();

        getActivity()
                .getWindow()
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

//        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        if (getArguments() != null && getArguments().getString("BarCode")  != null) {
            barCode = getArguments().getString("BarCode");

            edProductSeach.setText(barCode);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    //implementar a busca por nome/nome parcial

                    List<ProductEntity> productEntities = new ArrayList<>();
                    ProductEntity productEntity = GlobalState.getDB().productDao().findByBarCode(barCode);

                    if(productEntity != null) {
                        productEntities = new ArrayList<>();
                        productEntities.add(productEntity);
                    }
                    products.postValue(productEntities);

                }
            }).start();
        }

        return view;

    }


    private void createList() {
//        LinearLayout ll_card_account_container = view.findViewById(R.id.ll_products_container);
//        String name, eAccountType accountType, Activity activity, LinearLayout parent
//        parent.addView(viewChildLayout);

        new Thread(new Runnable() {
            @Override
            public void run() {
//
//
//                GlobalState.getDB().productBrandDao().deleteAll();
//                GlobalState.getDB().productDao().deleteAll();
//
//                ProductEntity productEntity;
//
//                productEntity = new ProductEntity();
////                productEntity.ID = UUID.randomUUID().toString();
//                productEntity.Name = "Palito Dente";
//                productEntity.BarCode = "7891334150027";
//                GlobalState.getDB().productDao().insertAll(productEntity);
//
//                productEntity = new ProductEntity();
////                productEntity.ID = UUID.randomUUID().toString();
//                productEntity.Name = "Produto 01";
//                productEntity.BarCode = "784547981";
//                GlobalState.getDB().productDao().insertAll(productEntity);
//
//                productEntity = new ProductEntity();
////                productEntity.ID = UUID.randomUUID().toString();
//                productEntity.Name = "Produto 02";
//                productEntity.BarCode = "784547982";
//                GlobalState.getDB().productDao().insertAll(productEntity);


                List<ProductEntity> productEntities = GlobalState.getDB().productDao().getAll();

                products.postValue(productEntities);
            }
        }).start();
    }

    public LiveData<List<ProductEntity>> getProducts() {

        return products;
    }


    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
//            Snackbar.make(mainLayout, "Camera access is required to display the camera preview.", Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
//                @Override public void onClick(View view) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.CAMERA
            }, MY_PERMISSION_REQUEST_CAMERA);
//                }
//            }).show();
        } else {
//            Snackbar.make(mainLayout, "Permission is not available. Requesting camera permission.", Snackbar.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.CAMERA
            }, MY_PERMISSION_REQUEST_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != MY_PERMISSION_REQUEST_CAMERA) {
            return;
        }

        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Snackbar.make(mainLayout, "Camera permission was granted.", Snackbar.LENGTH_SHORT).show();
//            initQRCodeReaderView();
        } else {
//            Snackbar.make(mainLayout, "Camera permission request was denied.", Snackbar.LENGTH_SHORT).show();
            //showMessage(getActivity(), "", "Sem permissão para acessar a câmera!", null);
            new AlertDialogCustom().showWarning(this.getContext(), "Erro de Permissão", "Sem permissão para acessar a câmera!");
        }
    }

    // Call Back method to get the Message form other Activity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null)
            return;

        String barCode = data.getStringExtra("RETURN_MSG");


        if (requestCode == EAN_REQUEST && resultCode == Activity.RESULT_OK) {
            edProductSeach.setText(barCode);


            new Thread(new Runnable() {
                @Override
                public void run() {
                    // Implementar a bsuca por nome/nome parcial

                    List<ProductEntity> productEntities = new ArrayList<>();
                    ProductEntity productEntity = GlobalState.getDB().productDao().findByBarCode(barCode);

                    if(productEntity != null) {
                        productEntities = new ArrayList<>();
                        productEntities.add(productEntity);
                    }
                    products.postValue(productEntities);

                }
            }).start();

        } else {

        }
    }
}