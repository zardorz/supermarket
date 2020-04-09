package br.com.bmgsistemas.supermarket.ui.cart;


import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.bmgsistemas.supermarket.MainActivity;
import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.SplashActivity;
import br.com.bmgsistemas.supermarket.core.AlertDialogCustom;
import br.com.bmgsistemas.supermarket.core.GlobalState;
import br.com.bmgsistemas.supermarket.entity.CartEntity;
import br.com.bmgsistemas.supermarket.entity.ProductEntity;
import br.com.bmgsistemas.supermarket.entity.ShoppingEntity;
import br.com.bmgsistemas.supermarket.entity.StoreEntity;
import br.com.bmgsistemas.supermarket.enums.eProductListItemClick;
import br.com.bmgsistemas.supermarket.enums.eShoppingStatus;
import br.com.bmgsistemas.supermarket.interfaces.OnCustomEventListener;
import br.com.bmgsistemas.supermarket.models.CartItem;
import br.com.bmgsistemas.supermarket.models.CartProduct;
import br.com.bmgsistemas.supermarket.models.Product;
import br.com.bmgsistemas.supermarket.models.Shopping;
import br.com.bmgsistemas.supermarket.ui.product.EANDecoderActivity;
import br.com.bmgsistemas.supermarket.ui.product.ProductAdapter;
import br.com.bmgsistemas.supermarket.ui.product.ProductDetailFragment;
import br.com.bmgsistemas.supermarket.ui.product.ProductFragment;
import br.com.bmgsistemas.supermarket.ui.shopping.ShoppingCheckOutFragment;
import br.com.bmgsistemas.supermarket.ui.shopping.ShoppingFragment;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    private View view;
    private ShoppingEntity shopping;
    private Menu menu;

    private static Runnable mHandlerDeleteCart;
    private static Runnable mHandlerDeleteShopping;

    private MutableLiveData<List<CartProduct>> cartProducts;

    private TextView edAmountCardTotal;
    private TextView edItensQtd;

    private MenuItem menuCheckOut;
    private MenuItem menuDeleteCart;
    private MenuItem menuDeleteShopping;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();

        MainActivity.setFragmentTitle("Meu Carrinho");
        MainActivity.showBackIcon();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cart, container, false);

        shopping = (ShoppingEntity) getArguments().getSerializable("ShoppingEntity");

        view.findViewById(R.id.container_Discount).setVisibility(View.GONE);
        view.findViewById(R.id.container_Paid).setVisibility(View.GONE);

        ((TextView) view.findViewById(R.id.edShoppingDate)).setText(shopping.getBuyDateFormated());
        ((TextView) view.findViewById(R.id.edShoppingLocal)).setText("");

        TextView edShoppingStatus = view.findViewById(R.id.edShoppingStatus);


        edShoppingStatus.setText(eShoppingStatus.getDescription(shopping.Status));


        switch (eShoppingStatus.fromInteger(shopping.Status)) {
            case OPEN:
                edShoppingStatus.setTextColor(getResources().getColor(R.color.green));
                break;

            case PAID:
                edShoppingStatus.setTextColor(getResources().getColor(R.color.gray_700));
                break;

        }

        if (shopping.Status != eShoppingStatus.OPEN.getNumericType()) {
            view.findViewById(R.id.container_Discount).setVisibility(View.VISIBLE);
            view.findViewById(R.id.container_Paid).setVisibility(View.VISIBLE);


            TextView edAmountDiscount = view.findViewById(R.id.edAmountDiscount);
            edAmountDiscount.setText(shopping.getTotalDiscountFormated());

            TextView edAmountPaid = view.findViewById(R.id.edAmountPaid);
            edAmountPaid.setText(shopping.getTotalPaidFormated());

            view.findViewById(R.id.btnNewProduct).setVisibility(View.GONE);
        }

        TextView edShoppingName = view.findViewById(R.id.edShoppingName);
        edShoppingName.setText(shopping.Name);

        edAmountCardTotal = view.findViewById(R.id.edAmountCardTotal);

        edItensQtd = view.findViewById(R.id.edItensQtd);

//        view.findViewById(R.id.imgCheckOutCart).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment fragment = new ShoppingCheckOutFragment();
//
//                Bundle bundle = new Bundle();
//                //bundle.putSerializable("ShoppingEntity", shopping);
//
//
//                bundle.putInt ("ItensQtd",Integer.parseInt(edItensQtd.getText().toString()));
//                bundle.putFloat("AmountTotal",Float.parseFloat(edAmountCardTotal.getText().toString().replace(".", "").replace(",", ".")));
//                bundle.putSerializable("ShoppingEntity",shopping);
//
//
//                fragment.setArguments(bundle);
//
//                getFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container, fragment)
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });
//        view.findViewById(R.id.imgDeleteCart).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new AlertDialogCustom().showConfirmAction(
//                        getActivity(),
//                        "Excluir Carrinho",
//                        "Deseja excluir o carrinho?",
//                        "Sim",
//                        "Não",
//                        mHandlerDeleteCart,
//                        null);
//
//            }
//        });

        view.findViewById(R.id.btnNewProduct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductFragment fragment = new ProductFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable("ShoppingEntity", shopping);
                fragment.setArguments(bundle);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


//        view.findViewById(R.id.imgShoppingDelete).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new AlertDialogCustom().showConfirmAction(
//                        getActivity(),
//                        "Excluir Compra",
//                        "Deseja excluir a compra?",
//                        "Sim",
//                        "Não",
//                        mHandlerDeleteShopping,
//                        null);
//            }
//        });

        mHandlerDeleteShopping = new Runnable() {
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        GlobalState.getDB().shoppingDao().delete(shopping);

                        Fragment fragment = new ShoppingFragment();

                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, fragment)
                                .commit();

                    }
                }).start();

            }
        };

        mHandlerDeleteCart = new Runnable() {
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        GlobalState.getDB().cartDao().deleteCardByShoppingID(shopping.ID);

                        getCartProducts();

                    }
                }).start();

            }
        };

        cartProducts = new MutableLiveData<List<CartProduct>>();

        getCartProducts().observe(this, localCartEntities -> {
            // Update UI.


            ////

            menuCheckOut = menu.findItem(R.id.menu_cart_checkout);
            menuDeleteCart = menu.findItem(R.id.menu_cart_delete);
            menuDeleteShopping = menu.findItem(R.id.menu_shopping_delete);

            Drawable iconCheckOut = menuCheckOut.getIcon();
            if (iconCheckOut != null) {
                iconCheckOut.mutate();
                iconCheckOut.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            }

            Drawable iconEmptyCart = menuDeleteShopping.getIcon();
            if (iconEmptyCart != null) {
                iconEmptyCart.mutate();
                iconEmptyCart.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            }

            Drawable iconDeleteCart = menuDeleteCart.getIcon();
            if (iconDeleteCart != null) {
                iconDeleteCart.mutate();
                iconDeleteCart.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            }

            /////


            List<CartItem> featuredCartItens = new ArrayList<CartItem>();

            float totalCart = 0.00f;

            for (CartProduct item : localCartEntities) {


                featuredCartItens.add(new CartItem(
                        item.ProductBarCode,
                        item.ProductName,
                        item.ProductQtd,
                        item.ProductAmount,
                        item.ProductTotalAmount));

                totalCart += item.ProductTotalAmount;
            }

            Locale ptBr = new Locale("pt", "BR");
            NumberFormat f = NumberFormat.getNumberInstance(ptBr);
            f.setMinimumFractionDigits(2);
            String amountFormated = f.format(totalCart);

            // Atualzia as quantidades
            edAmountCardTotal.setText(amountFormated);
            edItensQtd.setText(String.valueOf(localCartEntities.size()));

            RecyclerView bestRecyclerView = (RecyclerView) view.findViewById(R.id.cart_list);
            bestRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            bestRecyclerView.setHasFixedSize(true);
            bestRecyclerView.setNestedScrollingEnabled(false);

            CartItemAdapter mAdapter = new CartItemAdapter(GlobalState.getContext(), featuredCartItens);
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
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            CartProduct cartProduct = localCartEntities.get(position);

                            ProductEntity productEntity = GlobalState.getDB().productDao().findByBarCode(cartProduct.ProductBarCode);

//                          CartEntity cartEntity = GlobalState.getDB().cartDao().findByID (cartProduct.ProductBarCode);

                            Fragment fragment = new ProductCartDetailFragment();

                            Bundle bundle = new Bundle();

                            bundle.putSerializable("ProductEntity", productEntity);
                            bundle.putSerializable("ShoppingEntity", shopping);

                            bundle.putSerializable("CartProduct", cartProduct);

                            fragment.setArguments(bundle);

                            getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, fragment)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }).start();

                }

                @Override
                public void onEventAll(int value, int position) {
//                    switch (eProductListItemClick.fromInteger(value)){
//                        case EDIT:
//                            int x =1;
//                            break;
//
//                        case DELETE:
//                            int y =1;
//                            break;
//                    }
                }

            });

            if (localCartEntities.size() == 0) {

                disableCheckOut();
                disableDeleteCart();
//                disableDeleteShopping();

                ViewSwitcher viewSwitcher = view.findViewById(R.id.switcher);
                viewSwitcher.showNext();

                return;
            }

            if ((shopping.Status == eShoppingStatus.PAID.getNumericType()) || (shopping.Status == eShoppingStatus.CANCELED.getNumericType())) {

                disableCheckOut();
                disableDeleteCart();
                disableDeleteShopping();

                ViewSwitcher viewSwitcher = view.findViewById(R.id.switcher);
                viewSwitcher.showNext();

                return;
            }

        });

        getCartProducts();

//        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.);

        InputMethodManager mImm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mImm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        mImm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setHasOptionsMenu(true);

        return view;
    }

    //Cria action menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.cart_menu_actions, menu);

        this.menu = menu;

//        menuCheckOut = menu.findItem(R.id.menu_cart_checkout);
        menuDeleteCart = menu.findItem(R.id.menu_cart_delete);
//        menuEmptyCart = menu.findItem(R.id.menu_cart_empty);
//
//        Drawable iconCheckOut = menuCheckOut.getIcon();
//        if(iconCheckOut != null) {
//            iconCheckOut.mutate();
//            iconCheckOut.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
//        }
//
//        Drawable iconEmptyCart = menuEmptyCart.getIcon();
//        if(iconEmptyCart != null) {
//            iconEmptyCart.mutate();
//            iconEmptyCart.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
//        }

        Drawable iconDeleteCart = menuDeleteCart.getIcon();
        if (iconDeleteCart != null) {
            iconDeleteCart.mutate();
            iconDeleteCart.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.menu_cart_checkout:
                checkOut();
                return true;

            case R.id.menu_cart_delete:
                deleteCart();
                return true;

            case R.id.menu_shopping_delete:
                deleteShopping();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void checkOut() {
        Fragment fragment = new ShoppingCheckOutFragment();

        Bundle bundle = new Bundle();
        //bundle.putSerializable("ShoppingEntity", shopping);


        bundle.putInt("ItensQtd", Integer.parseInt(edItensQtd.getText().toString()));
        bundle.putFloat("AmountTotal", Float.parseFloat(edAmountCardTotal.getText().toString().replace(".", "").replace(",", ".")));
        bundle.putSerializable("ShoppingEntity", shopping);


        fragment.setArguments(bundle);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();

    }

    private void deleteCart() {
        new AlertDialogCustom().showConfirmAction(
                getActivity(),
                "Excluir Carrinho",
                "Deseja excluir o carrinho?",
                "Sim",
                "Não",
                mHandlerDeleteCart,
                null);
    }

    private void deleteShopping() {
        new AlertDialogCustom().showConfirmAction(
                getActivity(),
                "Excluir Compra",
                "Deseja excluir a compra?",
                "Sim",
                "Não",
                mHandlerDeleteShopping,
                null);
    }

    private LiveData<List<CartProduct>> getCartProducts() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<CartProduct> cartEntities = GlobalState.getDB().cartDao().findCartProductByShoppingID(shopping.ID);

                StoreEntity store = GlobalState.getDB().storeDao().findByID(shopping.StoreID);

                if (store != null)
                    ((TextView) view.findViewById(R.id.edShoppingLocal)).setText(store.Name);

                cartProducts.postValue(cartEntities);

            }
        }).start();

        return cartProducts;
    }


    private void disableCheckOut() {
        menuCheckOut.setEnabled(false);

        Drawable iconCheckOut = menuCheckOut.getIcon();
        if (iconCheckOut != null) {
            iconCheckOut.mutate();
            iconCheckOut.setColorFilter(getResources().getColor(R.color.gray_700), PorterDuff.Mode.SRC_ATOP);
        }
    }

    private void disableDeleteShopping() {
        menuDeleteShopping.setEnabled(false);

        Drawable iconEmptyCart = menuDeleteShopping.getIcon();
        if (iconEmptyCart != null) {
            iconEmptyCart.mutate();
            iconEmptyCart.setColorFilter(getResources().getColor(R.color.gray_700), PorterDuff.Mode.SRC_ATOP);
        }
    }

    private void disableDeleteCart() {
        menuDeleteCart.setEnabled(false);

        Drawable iconEmptyCart = menuDeleteCart.getIcon();
        if (iconEmptyCart != null) {
            iconEmptyCart.mutate();
            iconEmptyCart.setColorFilter(getResources().getColor(R.color.gray_700), PorterDuff.Mode.SRC_ATOP);
        }
    }
}
