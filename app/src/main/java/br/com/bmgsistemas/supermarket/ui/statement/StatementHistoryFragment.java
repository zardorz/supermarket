package br.com.bmgsistemas.supermarket.ui.statement;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.enums.eServiceMethods;
import br.com.bmgsistemas.supermarket.helpers.HTTPRequest;
import br.com.bmgsistemas.supermarket.models.APIResponse;
import br.com.bmgsistemas.supermarket.models.Account;
import br.com.bmgsistemas.supermarket.ui.account.AccountAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import br.com.bmgsistemas.supermarket.models.Statement;

public class StatementHistoryFragment extends Fragment {

    private SectionedRecyclerViewAdapter sectionAdapter;
    private View view;

    private Spinner spnMonth;
    private Spinner spnYear;
    private Spinner spnFinanceAccount;
    private AccountAdapter financeAccountsAdapter;
    private Activity activity;

    private boolean isStartup;

    private Account selectedFinanceAccountItem;

    public StatementHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        doLoadFinanceAccounts();

        if(selectedFinanceAccountItem != null)
            doLoadStatementGetHistory();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isStartup = true;
        activity = getActivity();

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_statement_history, container, false);

        //region spnMonth
        spnMonth = (Spinner) view.findViewById(R.id.spnMonth);
        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.months_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.months_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        spnMonth.setAdapter(adapter);

        spnMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
//                if(position > 0){
                // Notify the selected item text
                doLoadStatementGetHistory();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion

        //region spnYear
        spnYear = (Spinner) view.findViewById(R.id.spnYear);
        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.months_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(getActivity(),R.array.years_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        spnYear.setAdapter(adapterYear);

        spnYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    doLoadStatementGetHistory();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion

        //region spnFinanceAccount
        spnFinanceAccount = (Spinner) view.findViewById(R.id.spnFinanceAccount);
        spnFinanceAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedFinanceAccountItem  = (Account) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
//                if(position > 0){
                // Notify the selected item text
                doLoadStatementGetHistory();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion

        return view;
    }

    //region StatementsHistory
    private void doLoadStatementGetHistory() {
//        Loader.show(activity);

        if(isStartup)
            return;

        if(selectedFinanceAccountItem == null)
            return;

        HashMap<String, Object> postParameters = new HashMap<String, Object>();

        int accountID = selectedFinanceAccountItem.getId();
        String month = String.valueOf(spnMonth.getSelectedItemPosition() + 1);
        month = ("00" + month).substring(month.length());
        String year = spnYear.getSelectedItem().toString();
        int mesRef = Integer.parseInt(year.concat(month));

        postParameters.put("accountID", accountID);
        postParameters.put("period", mesRef);


        new HTTPRequest(getActivity(), eServiceMethods.STATEMENT_GETBYPERIOD) {
            protected void onPostExecute(APIResponse apiResponse) {
                onResultRequest(apiResponse);
            }
        }.execute(postParameters);
    }

    private void onResultRequest(APIResponse apiResponse) {
        Gson gson = new GsonBuilder().serializeNulls().create();

//        Loader.dismiss();
        if (apiResponse != null && apiResponse.Success) {
            // Se ocorreu sucesso código foi validado

//            Intent intent = new Intent(getApplicationContext(), TypeActivity.class);
//            startActivity(intent);

            String json = gson.toJson(apiResponse.Result);
//            Statement statement = gson.fromJson(json, Statement.class);

            List<Statement> statements = gson.fromJson(json, new TypeToken<List<Statement>>(){}.getType());

            ShowStatementsHistory(statements);

        } else {
            //Caso contrário ocorreu erro

//            List<Error> errors = apiResponse.Errors;
//            String msg  = "";
//
//            for (Error item : errors) {
//                msg ="("  + String.valueOf(item.getCode()) + ") " + item.getMessageTranslated();
//
//                if (gs.isFinishActivityCode(item.getCode())) {
//                    showTimeoutError(activity, true);
//                    return;
//                }
//
//                if(item.getCode() == -5000) {
//                    // Codigo de Validacao Invalido
//
//                    showMessage("Erro", msg, mHandlerRetryValidadeSMS);
//
//                    return;
//                }
//            }
//
//            showMessage("Erro", msg, null);

        }
    }

    private void ShowStatementsHistory(List<Statement>  statements) {

        List<Statement> itens;
        String sectionName;

        sectionAdapter = new SectionedRecyclerViewAdapter();

        //*****************

//        itens = new ArrayList<Statement>();

        sectionAdapter.addSection(new SectionStatementHistory(statements));

        //***********************************************
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionAdapter);
    }
    //endregion

    //region FinanceAccounts
    private void doLoadFinanceAccounts() {
//        Loader.show(activity);

        HashMap<String, Object> postParameters = new HashMap<String, Object>();
//        postParameters.put("accountID", 1);
//        postParameters.put("period", 201807);


        new HTTPRequest(getActivity(), eServiceMethods.FINANCE_ACCOUNT_GETALL) {
            protected void onPostExecute(APIResponse apiResponse) {
                onResultRequestFinanceAccounts(apiResponse);
            }
        }.execute(postParameters);
    }

    private void onResultRequestFinanceAccounts(APIResponse apiResponse) {
        Gson gson = new GsonBuilder().serializeNulls().create();

//        Loader.dismiss();
        if (apiResponse != null && apiResponse.Success) {


            String json = gson.toJson(apiResponse.Result);
//            Statement statement = gson.fromJson(json, Statement.class);

            List<Account> financeAccounts = gson.fromJson(json, new TypeToken<List<Account>>(){}.getType());

            ShowFinanceAccounts(financeAccounts);

        } else {
            //Caso contrário ocorreu erro

//            List<Error> errors = apiResponse.Errors;
//            String msg  = "";
//
//            for (Error item : errors) {
//                msg ="("  + String.valueOf(item.getCode()) + ") " + item.getMessageTranslated();
//
//                if (gs.isFinishActivityCode(item.getCode())) {
//                    showTimeoutError(activity, true);
//                    return;
//                }
//
//                if(item.getCode() == -5000) {
//                    // Codigo de Validacao Invalido
//
//                    showMessage("Erro", msg, mHandlerRetryValidadeSMS);
//
//                    return;
//                }
//            }
//
//            showMessage("Erro", msg, null);

        }
    }

    private  void ShowFinanceAccounts(List<Account> financeAccounts ){

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int month = calendar.get(Calendar.MONTH) ;

        spnMonth.setSelection(month ,true);

//        int year = calendar.get(Calendar.YEAR) + 1;
        spnYear.setSelection(1,true);

        isStartup = false;

////        // Create an ArrayAdapter using the string array and a default spinner layout
////        financeAccountsAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.months_array, android.R.layout.simple_spinner_item);
////        // Specify the layout to use when the list of choices appears
//
//        financeAccountsAdapter = new FinanceAccountAdapter(getContext(), android.R.layout.simple_spinner_item,financeAccounts);
////        binding.list.setAdapter(laptopsAdapter);
//
//        financeAccountsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        spnFinanceAccount.setAdapter(financeAccountsAdapter);

//        String[] financeAccountsItens = (String[]) financeAccounts.toArray(new String[0]);
//        ArrayList<String> financeAccountsItens = new ArrayList<String>();
//
//        for(int ii = 0; ii< financeAccounts.size();ii++){
//            financeAccountsItens.add(financeAccounts.get(ii).getName());
//        }

//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>( getContext(),R.layout.spinner_item,financeAccountsItens){
//            @Override
//            public boolean isEnabled(int position){
////                if(position == 0)
////                {
////                    // Disable the first item from Spinner
////                    // First item will be use for hint
////                    return false;
////                }
////                else
////                {
//                    return true;
////                }
//            }
//            @Override
//            public View getDropDownView(int position, View convertView, ViewGroup parent) {
//                View view = super.getDropDownView(position, convertView, parent);
//                TextView tv = (TextView) view;
////                if(position == 0){
////                    // Set the hint text color gray
////                    tv.setTextColor(Color.GRAY);
////                }
////                else {
//                    tv.setTextColor(Color.BLACK);
////                }
//                return view;
//            }
//        };
        AccountAdapter adapter = new AccountAdapter(getContext(), R.layout.spinner_item, financeAccounts){
            @Override
            public boolean isEnabled(int position){
//                if(position == 0)
//                {
//                    // Disable the first item from Spinner
//                    // First item will be use for hint
//                    return false;
//                }
//                else
//                {
                return true;
//                }
            }
            @Override
            public View getDropDownView(int position, View convertView,ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
//                if(position == 0){
//                    // Set the hint text color gray
//                    tv.setTextColor(Color.GRAY);
//                }
//                else {
                tv.setTextColor(Color.BLACK);
//                }
                return view;
            }
        };

//        adapter.setDropDownViewResource(android.R.layout.support_simple_spinner_dropdown_item);
//        spnFinanceAccount.se .setAdapter(adapter);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spnFinanceAccount.setAdapter(adapter);
        spnFinanceAccount.setSelection(0,true);

    }


    //endregion


}
