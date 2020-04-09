package br.com.bmgsistemas.supermarket;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.UUID;

import br.com.bmgsistemas.supermarket.core.AlertDialogCustom;
import br.com.bmgsistemas.supermarket.core.GlobalState;
import br.com.bmgsistemas.supermarket.entity.AppEntity;
import br.com.bmgsistemas.supermarket.entity.EcommerceCategoryEntity;
import br.com.bmgsistemas.supermarket.entity.ManufacturerEntity;
import br.com.bmgsistemas.supermarket.entity.ProductBrandEntity;
import br.com.bmgsistemas.supermarket.entity.ProductCategoryEntity;
import br.com.bmgsistemas.supermarket.entity.ProductEntity;
import br.com.bmgsistemas.supermarket.entity.StoreEntity;
import br.com.bmgsistemas.supermarket.ui.account.AccountFragment;
import br.com.bmgsistemas.supermarket.ui.authentication.LogiFragment;
import br.com.bmgsistemas.supermarket.ui.cart.CartFragment;
import br.com.bmgsistemas.supermarket.ui.dashboard.DashBoardFragment;
import br.com.bmgsistemas.supermarket.ui.product.ProductFragment;
import br.com.bmgsistemas.supermarket.ui.shopping.ShoppingFragment;
import br.com.bmgsistemas.supermarket.ui.wizard.intro.PresentationActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public GlobalState gs;
    private static Activity activity;
    private static Toolbar toolbar;
    private static ActionBarDrawerToggle toggle;
    private static DrawerLayout drawer;
    private static Runnable mHandlerExitApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gs = (GlobalState) getApplication();

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        activity = this;

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        final View.OnClickListener originalToolbarListener = toggle.getToolbarNavigationClickListener();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Hard Code para evitar de votlar a tela de cadastro. Não encontrei outra forma pois o pop esta overlapando as telas
                if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof CartFragment) {
                    getSupportFragmentManager().popBackStack();
                    Fragment fragment = new ShoppingFragment();

                    replaceFragment(fragment);

                    return;
                }

                if (getSupportFragmentManager().getBackStackEntryCount() < 1) {
                    drawer.openDrawer(GravityCompat.START);
                } else {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                toggle.syncState();
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mHandlerExitApp = new Runnable() {
            public void run() {
//                session.setSession(new Authorization());

                Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(intent);

                finish();
            }
        };

        // Create a new Fragment to be placed in the activity layout
        Fragment fragment = new LogiFragment();// DashBoardFragment();
//        Fragment fragment = new PresentationFragment();
        replaceFragment(fragment);

//        Activity act = new PresentationActivity()
//        Intent intent = new Intent(getApplicationContext(), PresentationActivity.class);
//        startActivity(intent);

        fillDB();
    }


    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        // Hard Code para evitar de votlar a tela de cadastro. Não encontrei outra forma pois o pop esta overlapando as telas
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof CartFragment) {
            getSupportFragmentManager().popBackStack();

            Fragment fragment = new ShoppingFragment();

            replaceFragment(fragment);

            return;
        }

        if (count > 0) {
            getSupportFragmentManager().popBackStack();

            toggle.syncState();
        } else {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                doIsClose();
            }
        }

    }

    public void doIsClose() {
        new AlertDialogCustom().showConfirmAction(
                activity,
                "Encerrar",
                "Deseja encerrar a aplicação?",
                "Sim",
                "Não",
                mHandlerExitApp,
                null);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;

        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            fragment = new DashBoardFragment();

        } else if (id == R.id.nav_finance_account) {
            fragment = new AccountFragment();

        } else if (id == R.id.nav_products) {
            fragment = new ProductFragment();

        } else if (id == R.id.nav_shopping_cart) {
            fragment = new ShoppingFragment();


//        } else if (id == R.id.nav_statement) {
//            fragment = new StatementHistoryFragment();
//        } else if (id == R.id.nav_categories) {
//            fragment = new DashboardFragment();
//        } else if (id == R.id.nav_exit){
//            doIsClose();
//            return true;
//        } else if(id == R.id.nav_about)  {
//            fragment = new AboutFragment();
//        } else if(id == R.id.nav_release_statement){
//            fragment = new ReleaseStatementFragment();
        }

        replaceFragment(fragment);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public static Toolbar getToolbar() {
        return toolbar;
    }

    public static void setFragmentTitle(String fragmentTitle) {
        ((AppCompatActivity) activity).getSupportActionBar().setTitle(fragmentTitle);
    }

    public static void showHomeIcon() {
//        ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowHomeEnabled(true);

        toggle.syncState();
    }

    public static void showBackIcon() {
//        ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowHomeEnabled(true);

        ((AppCompatActivity) activity).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_24dp);
//        ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void fillDB() {

        new Thread(new Runnable() {
            @Override
            public void run() {

//                ShoppingEntity shop = GlobalState.getDB().shoppingDao().findByID("d1bac876-95ee-4812-bcba-6cfc7cfdbbca");
//                GlobalState.getDB().shoppingDao().delete(shop );

                AppEntity appEntity = GlobalState.getDB().appDao().get();

                if(appEntity != null && appEntity.Initialized )
                    return;

                appEntity = new AppEntity();
                appEntity.ID = UUID.randomUUID().toString();
                appEntity.Initialized  = true;
                GlobalState.getDB().appDao().insert(appEntity);

                GlobalState.getDB().shoppingDao().deleteAll();
                GlobalState.getDB().productBrandDao().deleteAll();
                GlobalState.getDB().productCategoryDao().deleteAll();
                GlobalState.getDB().productDao().deleteAll();
                GlobalState.getDB().storeDao().deleteAll();
                GlobalState.getDB().manufacturerDao().deleteAll();
                GlobalState.getDB().ecommerceCategoryDao().deleteAll();

                ProductBrandEntity productBrandEntity;
                ProductCategoryEntity productCategoryEntity;
                ProductEntity productEntity;
                ManufacturerEntity manufacturerEntity;
                EcommerceCategoryEntity ecommerceCategoryEntity;
                StoreEntity store;

// Store
                store = new StoreEntity();
                store.ID = UUID.randomUUID().toString();
                store.Name = "Carrefour Hipermercado";
                store.Description = "Ao lado do terminal João Dias";
                store.Neighborhood = "Vila Andrade";
                store.ZipCode = 05724030;
                store.City = "São Paulo";
                store.Address = "Av. Alberto Augusto Alves";
                store.Number = "50";
                store.Complement = "";
                store.State = "SP";

                GlobalState.getDB().storeDao().insert(store);

                store = new StoreEntity();
                store.ID = UUID.randomUUID().toString();
                store.Name = "Sonda Supermercados";
                store.Description = "No shopping Boa Vista";
                store.Neighborhood = "Boa Vista";
                store.ZipCode = 04747030;
                store.City = "São Paulo";
                store.Address = "Rua Darwin";
                store.Number = "47";
                store.Complement = "Boavista Shopping";
                store.State = "SP";


                GlobalState.getDB().storeDao().insert(store);


//                List<ProductEntity> p = GlobalState.getDB().productDao().findByBarCode("15037349");
//                GlobalState.getDB().productDao().delete(p.get(0));

                //https://cosmos.bluesoft.com.br/pesquisar?utf8=%E2%9C%93&q=omo


// manufacturer
                manufacturerEntity = new ManufacturerEntity();
                manufacturerEntity.ID = UUID.randomUUID().toString();
                manufacturerEntity.Name = "UNILEVER";
                GlobalState.getDB().manufacturerDao().insert(manufacturerEntity);

// ecommerce
                ecommerceCategoryEntity = new EcommerceCategoryEntity();
                ecommerceCategoryEntity.ID = UUID.randomUUID().toString();
                ecommerceCategoryEntity.Name = "Lava Roupas";

// productBrand
                productBrandEntity = new ProductBrandEntity();
                productBrandEntity.ID = UUID.randomUUID().toString();
                productBrandEntity.Name = "OMO";
                GlobalState.getDB().productBrandDao().insert(productBrandEntity);

// productCategory
                productCategoryEntity = new ProductCategoryEntity();
                productCategoryEntity.ID = UUID.randomUUID().toString();
                productCategoryEntity.Name = "Higiene/Limpeza";
                GlobalState.getDB().productCategoryDao().insert(productCategoryEntity);

                productCategoryEntity = new ProductCategoryEntity();
                productCategoryEntity.ID = UUID.randomUUID().toString();
                productCategoryEntity.Name = "Utensílios para o lar";
                GlobalState.getDB().productCategoryDao().insert(productCategoryEntity);


// product
                productEntity = new ProductEntity();
                productEntity.Name = "Detergente em Pó OMO Multiação Poder Acelerador 1kg";
                productEntity.BarCode = "7898422742783";
                productEntity.ProductBrandID = productBrandEntity.ID;
                productEntity.ManufacturerID = manufacturerEntity.ID;
                productEntity.ProductCategoryID = productCategoryEntity.ID;
                productEntity.NCM = "3402.20.00";
                GlobalState.getDB().productDao().insert(productEntity);

                productEntity = new ProductEntity();
                productEntity.Name = "Detergente Líquido OMO Multiação Poder Acelerador 3L";
                productEntity.BarCode = "7891150020689";
                productEntity.ProductBrandID = productBrandEntity.ID;
                productEntity.ManufacturerID = manufacturerEntity.ID;
                productEntity.ProductCategoryID = productCategoryEntity.ID;
                productEntity.NCM = "3402.20.00";
                GlobalState.getDB().productDao().insert(productEntity);

                productEntity = new ProductEntity();
                productEntity.Name = "Detergente em Pó OMO Multiação Poder Acelerador 500g";
                productEntity.BarCode = "7891038001205";
                productEntity.ProductBrandID = productBrandEntity.ID;
                productEntity.ManufacturerID = manufacturerEntity.ID;
                productEntity.ProductCategoryID = productCategoryEntity.ID;
                productEntity.NCM = "3402.20.00";
                GlobalState.getDB().productDao().insert(productEntity);

                productCategoryEntity = new ProductCategoryEntity();
                productCategoryEntity.ID = UUID.randomUUID().toString();
                productCategoryEntity.Name = "Detergentes para a roupa";
                GlobalState.getDB().productCategoryDao().insert(productCategoryEntity);

                productEntity = new ProductEntity();
                productEntity.Name = "Detergente em Pó OMO Multiação Poder Acelerador 2kg";
                productEntity.BarCode = "7891150008502";
                productEntity.ProductBrandID = productBrandEntity.ID;
                productEntity.ManufacturerID = manufacturerEntity.ID;
                productEntity.ProductCategoryID = productCategoryEntity.ID;
                productEntity.NCM = "3402.20.00";
                GlobalState.getDB().productDao().insert(productEntity);

                productEntity = new ProductEntity();
                productEntity.Name = "Detergente em Pó OMO 2800g";
                productEntity.BarCode = "7891150048478";
                productEntity.ProductBrandID = productBrandEntity.ID;
                productEntity.ManufacturerID = manufacturerEntity.ID;
                productEntity.ProductCategoryID = productCategoryEntity.ID;
                productEntity.NCM = "3402.20.00";
                GlobalState.getDB().productDao().insert(productEntity);

                productBrandEntity = new ProductBrandEntity();
                productBrandEntity.ID = UUID.randomUUID().toString();
                productBrandEntity.Name = "Theoto";
                GlobalState.getDB().productBrandDao().insert(productBrandEntity);

                manufacturerEntity = new ManufacturerEntity();
                manufacturerEntity.ID = UUID.randomUUID().toString();
                manufacturerEntity.Name = "Theoto";
                GlobalState.getDB().manufacturerDao().insert(manufacturerEntity);

                productEntity = new ProductEntity();
                productEntity.Name = "Palito de Mesa 200un";
                productEntity.BarCode = "7891334150027";
                productEntity.ProductBrandID = productBrandEntity.ID;
                productEntity.ManufacturerID = manufacturerEntity.ID;
                productEntity.ProductCategoryID = productCategoryEntity.ID;
                productEntity.NCM = "4421.90.00";
                GlobalState.getDB().productDao().insert(productEntity);


            }
        }).start();

    }
}
