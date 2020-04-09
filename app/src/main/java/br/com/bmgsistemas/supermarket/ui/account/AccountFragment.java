package br.com.bmgsistemas.supermarket.ui.account;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.bmgsistemas.supermarket.MainActivity;
import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.enums.eAccountType;
import br.com.bmgsistemas.supermarket.enums.eServiceMethods;
import br.com.bmgsistemas.supermarket.helpers.HTTPRequest;
import br.com.bmgsistemas.supermarket.models.APIResponse;
import br.com.bmgsistemas.supermarket.models.Account;
import br.com.bmgsistemas.supermarket.partial.CardAccountsResume;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private View view;
    private Activity activity;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();

//        Toolbar toolbar = MainActivity.getToolbar(); // (Toolbar) view.findViewById(R.id.toolbar);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Contas");

//        doLoadFinanceAccounts();

        MainActivity.setFragmentTitle("Contas");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        activity = (Activity) getActivity();

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_finance_account, container, false);


//        NestedScrollView scrollView = view.findViewById(R.id.scroll);


        LinearLayout ll_card_account_container = view.findViewById(R.id.ll_card_account_container);

        List<CardAccountsResume> lstCardAccountsResume = new ArrayList<CardAccountsResume>();

        CardAccountsResume cardAccount = null;

        cardAccount = new CardAccountsResume("Minha Carteira", eAccountType.WALLET, activity, ll_card_account_container);
        lstCardAccountsResume.add(cardAccount);

        cardAccount = new CardAccountsResume("Minha Poupança", eAccountType.SAVE, activity, ll_card_account_container);
        lstCardAccountsResume.add(cardAccount);

        cardAccount = new CardAccountsResume("Minha Conta Corrente", eAccountType.CHECKING_ACCOUNT, activity, ll_card_account_container);
        lstCardAccountsResume.add(cardAccount);

        cardAccount = new CardAccountsResume("Itau Silvio", eAccountType.CHECKING_ACCOUNT, activity, ll_card_account_container);
        lstCardAccountsResume.add(cardAccount);

        cardAccount = new CardAccountsResume("Itau Rosane", eAccountType.CHECKING_ACCOUNT, activity, ll_card_account_container);
        lstCardAccountsResume.add(cardAccount);

        cardAccount = new CardAccountsResume("Meu Cartão de Crédito", eAccountType.CREDIT_CARD , activity, ll_card_account_container);
        lstCardAccountsResume.add(cardAccount);


//        RecyclerView ltvFinanceAccounts = view.findViewById(R.id.ltvFinanceAccounts);


        /*
            <include
                layout="@layout/card_general_view"
                style="@style/cardview_item"/>
         */

        return view;

    }


//
//    private void doLoadFinanceAccounts() {
////        Loader.show(activity);
//
//        HashMap<String, Object> postParameters = new HashMap<String, Object>();
////        postParameters.put("accountID", 1);
////        postParameters.put("period", 201807);
//
//
//        new HTTPRequest(getActivity(), eServiceMethods.FINANCE_ACCOUNT_GETALL) {
//            protected void onPostExecute(APIResponse apiResponse) {
//                onResultRequest(apiResponse);
//            }
//        }.execute(postParameters);
//    }
//
//    private void onResultRequest(APIResponse apiResponse) {
//        Gson gson = new GsonBuilder().serializeNulls().create();
//
////        Loader.dismiss();
//        if (apiResponse != null && apiResponse.Success) {
//
//
//            String json = gson.toJson(apiResponse.Result);
////            Statement statement = gson.fromJson(json, Statement.class);
//
//            List<Account> accounts = gson.fromJson(json, new TypeToken<List<Account>>(){}.getType());
//
////            ShowFinanceAccounts(financeAccounts);
//
//        } else {
//            //Caso contrário ocorreu erro
//
////            List<Error> errors = apiResponse.Errors;
////            String msg  = "";
////
////            for (Error item : errors) {
////                msg ="("  + String.valueOf(item.getCode()) + ") " + item.getMessageTranslated();
////
////                if (gs.isFinishActivityCode(item.getCode())) {
////                    showTimeoutError(activity, true);
////                    return;
////                }
////
////                if(item.getCode() == -5000) {
////                    // Codigo de Validacao Invalido
////
////                    showMessage("Erro", msg, mHandlerRetryValidadeSMS);
////
////                    return;
////                }
////            }
////
////            showMessage("Erro", msg, null);
//
//        }
//    }


}
