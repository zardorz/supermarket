package br.com.bmgsistemas.supermarket.ui.shopping;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ViewSwitcher;


import java.util.ArrayList;
import java.util.List;

import br.com.bmgsistemas.supermarket.MainActivity;
import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.core.GlobalState;
import br.com.bmgsistemas.supermarket.entity.ShoppingEntity;
import br.com.bmgsistemas.supermarket.enums.eShoppingStatus;
import br.com.bmgsistemas.supermarket.interfaces.OnCustomEventListener;
import br.com.bmgsistemas.supermarket.models.Shopping;
import br.com.bmgsistemas.supermarket.ui.cart.CartFragment;


public class ShoppingFragment extends Fragment {

    private View view;
//    private ProductDao productDao;
    private MutableLiveData<List<Shopping>> shoppings;

    private eShoppingStatus filterStatus;


    public ShoppingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        MainActivity.setFragmentTitle("Compra Realizadas");
        MainActivity.showHomeIcon();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        filterStatus = eShoppingStatus.ALL;

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);


        FloatingActionButton fab = view.findViewById(R.id.btnNewCart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                ShoppingAddNewFragment  fragment = new ShoppingAddNewFragment();

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });

        loadCarts();

        refreshList();

        InputMethodManager mImm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mImm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        mImm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

        setHasOptionsMenu(true);

        return view;
    }

    private  void loadCarts(){
        shoppings = new MutableLiveData<List<Shopping>>();
        getShoppings().observe(this, featuredShoppings -> {
            // Update UI.
//            List<Shopping> featuredShoppings  = new ArrayList<Shopping>();
//
//
//            for (Shopping item : localShoppings) {
//                featuredShoppings.add(new Shopping(item));
//            }

            RecyclerView bestRecyclerView = (RecyclerView) view.findViewById(R.id.cart_list);

            bestRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            bestRecyclerView.setHasFixedSize(true);
            bestRecyclerView.setNestedScrollingEnabled(false);

            ShoppingAdapter mAdapter = new ShoppingAdapter(GlobalState.getContext(), featuredShoppings);
            bestRecyclerView.setAdapter(mAdapter);

            mAdapter.setOnCustomEventListener(new OnCustomEventListener() {
                @Override
                public void onEvent() {

                }

                @Override
                public void onEventPosition  (int position) {



                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Shopping item = shoppings.getValue().get(position);

                            ShoppingEntity shoppingEntity = GlobalState.getDB().shoppingDao().findEntityByID(item.getId());

                            CartFragment fragment = new CartFragment();

                            Bundle bundle = new Bundle();
                            bundle.putSerializable("ShoppingEntity", shoppingEntity);
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
                public void onEventValue  (int value) {

                }

                @Override
                public void onEventAll(int value, int position) {

                }

            });

            ViewSwitcher viewSwitcher = view.findViewById(R.id.switcher);
            viewSwitcher .reset();

            if (featuredShoppings.size() == 0) {

                viewSwitcher.showNext();

                return;
            }

            viewSwitcher.setDisplayedChild(0);
        });
    }


    //Cria action menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.shopping_menu_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.menu_shopping_search:
                return true;

            case R.id.menu_shopping_filter_all:
                filterStatus = eShoppingStatus.ALL;
                refreshList();
                return true;

            case R.id.menu_shopping_filter_open:
                filterStatus = eShoppingStatus.OPEN;
                refreshList();
                return true;

            case R.id.menu_shopping_filter_paid:
                filterStatus = eShoppingStatus.PAID;
                refreshList();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public LiveData<List<Shopping>> getShoppings() {

        return shoppings;
    }

    private void refreshList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Shopping> shoppingListFilterd;
                List<Shopping> shoppingList = GlobalState.getDB().shoppingDao().getAllByBuyDateDesc();

                shoppingListFilterd = shoppingList;

                switch (filterStatus) {
                    case OPEN:
                    case PAID:
                    case CANCELED:
                        shoppingListFilterd = new ArrayList<>();

                        for (Shopping s : shoppingList) {
                            if (s.getStatus() == filterStatus.getNumericType()) {
                                shoppingListFilterd.add(s);
                            }
                        }

                        break;
                }

                shoppings.postValue(shoppingListFilterd);

            }
        }).start();
    }

}
