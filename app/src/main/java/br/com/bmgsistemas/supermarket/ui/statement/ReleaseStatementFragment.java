package br.com.bmgsistemas.supermarket.ui.statement;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.enums.eServiceMethods;
import br.com.bmgsistemas.supermarket.enums.eStatementOperationType;
import br.com.bmgsistemas.supermarket.enums.eStatementType;
import br.com.bmgsistemas.supermarket.helpers.CurrencyMask;
import br.com.bmgsistemas.supermarket.helpers.HTTPRequest;
import br.com.bmgsistemas.supermarket.models.APIResponse;
import br.com.bmgsistemas.supermarket.models.Account;
import br.com.bmgsistemas.supermarket.ui.account.AccountAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReleaseStatementFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private View view;
    private Spinner spnFinanceAccountFrom;
    private boolean isDebit = true;
    private String movimentDateParam;
    private TextView edMovimentDate;
    private EditText edAmount;
    private float amountValue = 0;

    private Account selectedFinanceAccountItem;
    private  String description;
    private Activity activity;



    public ReleaseStatementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_release_statement, container, false);
        activity = getActivity();

        ((CheckBox) view.findViewById(R.id.chkStatementDebit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                isDebit = ((CheckBox) view).isChecked();
                isDebit = false;
                if (((CheckBox) v).isChecked()) {
                    isDebit = true;
                }
            }
        });



//        if(session.getRememberMe())
//            ((CheckBox)findViewById(R.id.chkRememberMe)).setChecked(true);


        spnFinanceAccountFrom = view.findViewById(R.id.spnFinanceAccountFrom);
        spnFinanceAccountFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedFinanceAccountItem  = (Account) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
//                if(position > 0){
                // Notify the selected item text
//                doLoadStatementGetHistory();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnSaveStatement = view.findViewById(R.id.btnSaveStatement);
        btnSaveStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidFields())
                    return;

                doSaveStatement();

            }

        });

        Date cDate = new Date();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(cDate);

        edMovimentDate = view.findViewById(R.id.edMovimentDate);
        edMovimentDate.setFocusable(false);
        edMovimentDate.setText(date);
        edMovimentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        edAmount = view.findViewById(R.id.edAmount);

        edAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
//                    edAmount.setText("0,00");
//                    edAmount.setSelection(edAmount.getText().length());
                    edAmount.post(new Runnable() {
                        @Override
                        public void run() {
                            edAmount.setSelection(edAmount.getText().length());
                        }
                    });
                }
            }
        });

        edAmount.addTextChangedListener(new CurrencyMask(edAmount ));


        loadStatus();
        loadFinanceAccounts();

        return view;
    }

    private boolean isValidFields() {

//        edAmount.setText("1.25");

//        EditText edEmail = view.findViewById(R.id.edEmail);
        EditText edMovimentDate = view.findViewById(R.id.edMovimentDate);

        EditText edDescription = view.findViewById(R.id.edDescription);
//        edDescription.setText("ttt");

        TextInputLayout edMovimentDateView = view.findViewById(R.id.edMovimentDateView);
        TextInputLayout edDescriptionView = view.findViewById(R.id.edDescriptionView);
        TextInputLayout edAmountView = view.findViewById(R.id.edAmountView);

        edMovimentDateView.setError(null);
        edDescriptionView.setError(null);
        edAmountView.setError(null);

//        edEmail.setText("zardorz@hotmail.com");
//        edPassword.setText("123456");
//
//        String email = edEmail.getText().toString();
//        String password = edPassword.getText().toString();
//
        String inputDateStr = edMovimentDate.getText().toString();

        //DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sszzzz");
        DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = inputFormat.parse(inputDateStr);
            movimentDateParam = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        description = edDescription.getText().toString();
        String amount = edAmount.getText().toString().trim();

        if (TextUtils.isEmpty(description)) {
            edDescriptionView.setError("Campo Obrigatório!");
            return false;
        }


        if (amount == null || TextUtils.isEmpty(amount)) {
            edAmountView.setError("Valor Inválido!");
            return false;
        } else {
            amount = amount.replace(".", "").replace(",", ".").replace("R$","");
            amountValue = Float.valueOf(amount);
        }


//        spnFinanceAccountFrom

        return true;

    }

    private void loadStatus() {
        // Get reference of widgets from XML layout
        Spinner spinner = view.findViewById(R.id.spnStatus);

        // Initializing a String Array
//        int eStatementStatusSize = eStatementStatus.values().length;

//        List<String> statusItens = new List<String>();
//
//        for(int ii=0;ii < eStatementStatusSize; ii++){
//            statusItens.add(eStatementStatus.fromInteger(ii));
//        }

        String[] statusItens = new String[]{
                "Aberto",
                "Conciliado",
                "ReConciliado",
                "Importado"
        };

        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, statusItens);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }


    //**************

    //region FinanceAccounts
    private void loadFinanceAccounts() {

        HashMap<String, Object> postParameters = new HashMap<String, Object>();


        //getActivity(),
        new HTTPRequest(activity, eServiceMethods.FINANCE_ACCOUNT_GETALL) {
            protected void onPostExecute(APIResponse apiResponse) {
                onResultRequestFinanceAccounts(apiResponse);
            }
        }.execute(postParameters);
    }

    private void onResultRequestFinanceAccounts(APIResponse apiResponse) {
        Gson gson = new GsonBuilder().serializeNulls().create();

        if (apiResponse != null && apiResponse.Success) {


            String json = gson.toJson(apiResponse.Result);

            List<Account> financeAccounts = gson.fromJson(json, new TypeToken<List<Account>>() {
            }.getType());

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

    private void ShowFinanceAccounts(List<Account> financeAccounts) {


        AccountAdapter adapter = new AccountAdapter(getContext(), R.layout.spinner_item, financeAccounts) {
            @Override
            public boolean isEnabled(int position) {

                return true;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(Color.BLACK);
                return view;
            }
        };

        adapter.setDropDownViewResource(R.layout.spinner_item);
        spnFinanceAccountFrom.setAdapter(adapter);
        spnFinanceAccountFrom.setSelection(0, true);

    }

    //**************


    private void doSaveStatement() {
        HashMap<String, Object> postParameters = new HashMap<String, Object>();
        postParameters.put("flag", 0);
        postParameters.put("statementStatus", 1);
        postParameters.put("date", movimentDateParam);

        if(isDebit)
            postParameters.put("operationType", eStatementOperationType.DEBIT.getNumericType());
        else
            postParameters.put("operationType", eStatementOperationType.CREDIT.getNumericType());

        postParameters.put("financeAccountID", selectedFinanceAccountItem.getId());
        postParameters.put("document", null);
        postParameters.put("description", description);
        postParameters.put("memo", null);
        postParameters.put("categoryID", null);
        postParameters.put("subCategoryID", null);

        if(isDebit) {
            postParameters.put("creditAmount", 0);
            postParameters.put("debitAmount", amountValue);
        }else {
            postParameters.put("creditAmount", amountValue);
            postParameters.put("debitAmount", 0);
        }
//        postParameters.put("amount", accountID);

        if(isDebit)
            postParameters.put("statementType", eStatementType.DEBIT.getNumericType());
        else
            postParameters.put("statementType", eStatementType.CREDIT.getNumericType());


        postParameters.put("statementTransferency", null);


//        getActivity(),
        new HTTPRequest(activity, eServiceMethods.STATEMENT_CREATE) {
            protected void onPostExecute(APIResponse apiResponse) {
                onResultRequest(apiResponse);
            }
        }.execute(postParameters);
    }

    private void doUpdateStatement() {
        HashMap<String, Object> postParameters = new HashMap<String, Object>();
        postParameters.put("flag", 0);
        postParameters.put("statementStatus", 1);
        postParameters.put("date", movimentDateParam);

        if(isDebit)
            postParameters.put("operationType", eStatementOperationType.DEBIT.getNumericType());
        else
            postParameters.put("operationType", eStatementOperationType.CREDIT.getNumericType());

        postParameters.put("financeAccountID", selectedFinanceAccountItem.getId());
        postParameters.put("document", null);
        postParameters.put("description", description);
        postParameters.put("memo", null);
        postParameters.put("categoryID", null);
        postParameters.put("subCategoryID", null);

        if(isDebit) {
            postParameters.put("creditAmount", 0);
            postParameters.put("debitAmount", amountValue);
        }else {
            postParameters.put("creditAmount", amountValue);
            postParameters.put("debitAmount", 0);
        }
//        postParameters.put("amount", accountID);

        if(isDebit)
            postParameters.put("statementType", eStatementType.DEBIT.getNumericType());
        else
            postParameters.put("statementType", eStatementType.CREDIT.getNumericType());


        postParameters.put("statementTransferency", null);


        //getActivity(),
        new HTTPRequest(activity, eServiceMethods.STATEMENT_UPDATE) {
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


            String json = gson.toJson(apiResponse.Result);
//            Statement statement = gson.fromJson(json, Statement.class);

            List<Statement> statements = gson.fromJson(json, new TypeToken<List<Statement>>(){}.getType());

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

    //***************
    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(getActivity(),  R.style.date_picker , this, year, month, day);
        dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        dialog.show();
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String dayFormated = ("00" + String.valueOf(day)).substring( String.valueOf(day).length());
        String monthFormated = ("00" + String.valueOf(month + 1)).substring( String.valueOf(month + 1).length());
        String dateFormated = dayFormated.concat("/").concat(monthFormated).concat("/").concat(String.valueOf(year));
        String dateParamFormated = String.valueOf(year).concat("-").concat(monthFormated).concat("-").concat(dayFormated);


        edMovimentDate.setText(dateFormated);
        //movimentDateParam= Integer.parseInt(dateParamFormated);
        movimentDateParam= dateParamFormated;

    }

}
