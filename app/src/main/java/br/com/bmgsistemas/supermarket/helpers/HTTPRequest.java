package br.com.bmgsistemas.supermarket.helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.Window;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;

import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.core.GlobalState;
import br.com.bmgsistemas.supermarket.core.SessionManager;
import br.com.bmgsistemas.supermarket.enums.eServiceMethods;
import br.com.bmgsistemas.supermarket.models.APIResponse;
import br.com.bmgsistemas.supermarket.models.Error;

public class HTTPRequest extends AsyncTask<HashMap<String, Object>, Void  , APIResponse> {
    private AlertDialog alert;
    private String msgError = "";

    private final static String PROTOCOL_DEV = "http://";
    private final static String PROTOCOL_PROD = "https://";
    private final static String BASEADDRESS_DEV = "192.168.1.101";
    //    private final static String BASEADDRESS_DEV = "services.supermarket.com.br";
    //                    //DEV  10.0.2.2";  //
    private final static String BASEADDRESS_PROD = "services.supermarket.com.br";   //PROD
    private final static String PORT_RELEASE = "";                          //RELEASE
    //    private final static String PORT_DEBUG = ":5002";                       //DEBUG
    private final static String PORT_DEBUG = "";                       //DEBUG

    private String PROTOCOL;
    private String PORT ;
    private String BASEADDRESS;

    private eServiceMethods serviceMethod;
    private APIResponse apiResponse  = null;
    private Context context;
    private Activity activity;
    private static SessionManager session ;
//    private static ProgressDialog progressBar;


    public HTTPRequest ( Activity activity,eServiceMethods serviceMethod ) {
        this.serviceMethod = serviceMethod;
        this.activity = activity;
        session = GlobalState.getSession();

//        showLoader();
    }

    public  APIResponse doRequest( HashMap<String, Object> postParameters ) {
        try {

            Gson gson =  new GsonBuilder()
                    .enableComplexMapKeySerialization()
                    .setPrettyPrinting().create(); //new Gson();

            if (session.getIsDevelopment()) {
                BASEADDRESS = BASEADDRESS_DEV;
                PROTOCOL    = PROTOCOL_DEV;

                if (session.getIsDebugMode())
                    PORT = PORT_DEBUG;
                else
                    PORT = PORT_RELEASE;
            }else {
                PROTOCOL = PROTOCOL_PROD;
                BASEADDRESS = BASEADDRESS_PROD;
                PORT = PORT_RELEASE;
            }

            String urlService = PROTOCOL + BASEADDRESS + PORT;
            String actionMethod = "";
            String jsonParameters = "";

            HashMap<String, Object> serviceRequest = new HashMap<String, Object>();

            serviceRequest.clear();
            serviceRequest = postParameters;

            String requestMethod = "POST";


            //region Identifica o servico a ser executado
            switch (this.serviceMethod) {

                //region USER_ACCOUNT
                case USER_ACCOUNT_AUTHENTICATION:
                    urlService += "/useraccounts/Login";
                    break;

//                case USER_ACCOUNT_CREATE:
//                    urlService += "/users/register";
//                    break;
//
//                case  USER_ACCOUNT_LOGOUT:
//                    urlService += "/useraccounts/Register";
//                    requestMethod = "POST";
//                    break;
//
//                case  USER_ACCOUNT_UPDATE:
//                    urlService += "/api/accounts/Register";
//                    requestMethod = "UPDATE";
//                    break;
//
//                case  USER_ACCOUNT_DELETE:
//                    urlService += "/api/accounts/Register";
//                     requestMethod = "DELETE";
//                    break;
                //endregion

                //region CATEGORY
                case  CATEGORY_GETALL:
                    urlService += "/api/accounts/Register";
                    requestMethod = "GET";
                    break;

                case  CATEGORY_GETBYID:
                    urlService += "/api/accounts/Register";
                    requestMethod = "GET";
                    break;

                case  CATEGORY_GETBYPARENTID:
                    urlService += "/api/accounts/Register";
                    requestMethod = "GET";
                    break;

                case  CATEGORY_GETBYTYPE:
                    urlService += "/api/accounts/Register";
                    requestMethod = "GET";
                    break;

                case  CATEGORY_CREATE:
                    urlService += "/api/accounts/Register";
                    requestMethod = "POST";
                    break;

                case  CATEGORY_UPDATE:
                    urlService += "/api/accounts/Register";
                    requestMethod = "PUT";
                    break;

                case  CATEGORY_DELETE:
                    urlService += "/api/accounts/Register";
                    requestMethod = "DELETE";
                    break;
                //endregion

                //region FINANCE_ACCOUNT
                case  FINANCE_ACCOUNT:
                    urlService += "/api/accounts/Register";
                    requestMethod = "GET";
                    break;

                case  FINANCE_ACCOUNT_GETALL:
                    urlService += "/api/FinanceAccounts/GetAll";
                    requestMethod = "GET";
                    break;

                case  FINANCE_ACCOUNT_GETBYID:
                    urlService += "/api/accounts/Register";
                    requestMethod = "GET";
                    break;

                case  FINANCE_ACCOUNT_GETBYTYPE:
                    urlService += "/api/accounts/Register";
                    requestMethod = "GET";
                    break;

                case  FINANCE_ACCOUNT_GETBYNAME:
                    urlService += "/api/accounts/Register";
                    requestMethod = "GET";
                    break;

                case  FINANCE_ACCOUNT_CREATE:
                    urlService += "/api/accounts/Register";
                    requestMethod = "POST";
                    break;

                case  FINANCE_ACCOUNT_UPDATE:
                    urlService += "/api/accounts/Register";
                    requestMethod = "PUT";
                    break;

                case  FINANCE_ACCOUNT_DELETE:
                    urlService += "/api/accounts/Register";
                    requestMethod = "DELETE";
                    break;
                //endregion

                //region INSTITUITION
                case  INSTITUITION_GETALL:
                    urlService += "/api/accounts/Register";
                    requestMethod = "GET";
                    break;

                case  INSTITUITION_GETBYID:
                    urlService += "/api/accounts/Register";
                    requestMethod = "GET";
                    break;

                case  INSTITUITION_CREATE:
                    urlService += "/api/accounts/Register";
                    requestMethod = "POST";
                    break;

                case  INSTITUITION_UPDATE:
                    urlService += "/api/accounts/Register";
                    requestMethod = "PUT";
                    break;

                case  INSTITUITION_DELETE:
                    urlService += "/api/accounts/Register";
                    requestMethod = "DELETE";
                    break;
                //endregion

                //region STATEMENT
                case  STATEMENT_GETBYPERIOD:
                    urlService += "/api/statements/account/" + String.valueOf(postParameters.get("accountID")) + "/period/" + String.valueOf(postParameters.get("period"));
                    requestMethod = "GET";
                    break;

                case  STATEMENT_GETBYID:
                    urlService += "/api/statements/" + String.valueOf(postParameters.get("id"));
                    requestMethod = "GET";
                    break;

                case  STATEMENT_CREATE:
                    urlService += "/api/statements/add";
                    requestMethod = "POST";
                    break;

                case  STATEMENT_UPDATE:
                    urlService += "/api/statements/update";
                    requestMethod = "PUT";
                    break;

                case  STATEMENT_DELETE:
                    urlService += "/api/statements/" + String.valueOf(postParameters.get("id"));
                    requestMethod = "DELETE";
                    break;
                //endregion

                //region USER
                case  USER_GETALL:
                    urlService += "/api/accounts/Register";
                    requestMethod = "GET";
                    break;

                case  USER_GETBYID:
                    urlService += "/api/accounts/Register";
                    requestMethod = "GET";
                    break;

                case  USER_CREATE:
                    urlService += "/api/accounts/Register";
                    requestMethod = "POST";
                    break;

                case  USER_UPDATE:
                    urlService += "/api/accounts/Register";
                    requestMethod = "PUT";
                    break;

                case  USER_DELETE:
                    urlService += "/api/accounts/Register";
                    requestMethod = "DELETE";
                    break;
                //endregion

            }
            //endregion

            // http client
            URL url = null;
            url = new URL(urlService);

            HttpURLConnection httpClient = (HttpURLConnection) url.openConnection();

            httpClient.setRequestMethod(requestMethod);

            httpClient.setConnectTimeout(1000);
            httpClient.setUseCaches(false);
            httpClient.setDoInput(true);


            // set the header to get the data in JSON formate
            httpClient.setRequestProperty("Accept", "application/json");
            httpClient.setRequestProperty("Content-type", "application/json;charset=UTF-8");

//            if (session.getTokenID() != null) {
            if(urlService.toLowerCase().contains("api"))
                httpClient.setRequestProperty("Authorization", "Bearer " + session.getAuthorization().getAccessToken() );

//            if (session.getIsDevelopment())
//                httpClient.setRequestProperty("Host", "service.avalonlocal");

            jsonParameters = gson.toJson(serviceRequest);

            if(!requestMethod.equals("GET")) {
                httpClient.setDoOutput(true);

                OutputStream os = null;
                os = httpClient.getOutputStream();
                os.write(jsonParameters.getBytes());
                os.close();
            }

            httpClient.connect();

            //if (httpClient.getResponseCode() == HttpURLConnection.HTTP_OK)
            switch (httpClient.getResponseCode()) {

                case HttpURLConnection.HTTP_OK:
                    //receive & read data response
                    String json = null;

                    json  = getStringFromInputStream(httpClient.getInputStream());
                    json = json .replace("'", "\\'");
                    json = json .replace("\"", "\'");

                    //json = getStringFromInputStream(httpClient.getInputStream()).replace("\"", "'");

                    httpClient.disconnect();

                    try {

                        apiResponse = gson.fromJson(json, APIResponse.class);

                    }catch (Exception ex){
                        msgError  = "Ocorreu um problema ao acessar o servidor.\n Por favor, tente mais tarde.";

                        apiResponse = new APIResponse();
                        apiResponse.Success = false;
                        apiResponse.addError(new Error(500, msgError ));

                        Handler mHandler = new Handler(Looper.getMainLooper());
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                doShowErrorMessage(msgError );
                            }
                        });
                    }

                    break;

                case HttpURLConnection.HTTP_UNAUTHORIZED:
                    msgError = "Usuário ou senha inválidos!";

                    apiResponse = new APIResponse();
                    apiResponse.Success = false;
                    apiResponse.addError(new Error(401, msgError));

                    Handler mHandler = new Handler(Looper.getMainLooper());
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            doShowErrorMessage(msgError);
                        }
                    });


                    break;

                default:

                    break;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        }catch (SocketTimeoutException e) {
            // connection timed out...let's try again
            e.printStackTrace();

            msgError = "Ocorreu um TimeOut.\n Por favor, tente novamente.";

            apiResponse = new APIResponse();
            apiResponse.Success = false;
            apiResponse.addError(new Error(408, msgError));

            Handler mHandler = new Handler(Looper.getMainLooper());
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    doShowErrorMessage(msgError);
                }
            });

        } catch (IOException e){
            e.printStackTrace();

            msgError  = "Ocorreu um problema ao acessar o servidor.\n Por favor, tente mais tarde.";

            apiResponse = new APIResponse();
            apiResponse.Success = false;
            apiResponse.addError(new Error(500, msgError ));

            Handler mHandler = new Handler(Looper.getMainLooper());
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    doShowErrorMessage(msgError );
                }
            });

        }finally {
//            HideProgress();
            dismissLoader();
        }

        return apiResponse;
    }

    private void doShowErrorMessage(String message) {
        //Cria o gerador do AlertDialog
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity.getApplicationContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(activity, R.style.alert_dialog));


        builder.setCancelable(false);

        //define o titulo
        builder.setTitle("Aviso");

        //define a mensagem
        builder.setMessage(message );

        //define um botão como positivo
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //silvio
//                Intent intent = new Intent(activity, LoginActivity.class);
////                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                activity.startActivity(intent);
//                activity.finish();
            }
        });
//        //define um botão como negativo.
//        builder.setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface arg0, int arg1) {
//                Toast.makeText(MainActivity.this, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
//            }
//        });

        alert = builder.create();
        alert.show();
    }



    @Override
    protected APIResponse doInBackground(HashMap<String, Object>... postParameters ) {

        apiResponse = new APIResponse();

        if (!GlobalState.isConnected()){

//            HideProgress();
            return apiResponse;
        }

        return doRequest( postParameters[0]);
    }

    @NonNull
    private String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

    @Override
    protected void onPreExecute(){
        showLoader();
    }

    private ProgressDialog progressBar = null;
    public  void showLoader(){


//        if(progressBar != null){
//            return;
////            progressBar.dismiss();
////            progressBar = null;
//        }

        if (activity.isFinishing()){
            return;
        }

        if ( progressBar != null && !progressBar.isShowing()) {
            progressBar.show();
            return;
        }

        if ( progressBar != null && progressBar.isShowing()) {
            return;
        }


        progressBar = new ProgressDialog(activity  , R.style.LoaderTheme);
        progressBar.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressBar.setIndeterminate(true);
        progressBar.setCancelable(false);

        progressBar.show();
        progressBar.setContentView(R.layout.loader);

    }

    public  void dismissLoader() {

        Handler mHandler = new Handler(Looper.getMainLooper());

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if ( !activity.isFinishing() && progressBar != null && progressBar.isShowing()) {
                    try {
                        progressBar.dismiss();
                        progressBar = null;
                    }catch (Exception ex){
                        progressBar.hide();
                    }

//                    progressBar = null;
                    return;
                }

                progressBar.dismiss();
                progressBar = null;

//                if ( !activity.isFinishing() && progressBar != null ) {
//                    progressBar = null;
//                }

            }
        }, 200);
    }

//    @Override
//    protected void onDestroy() {
//        progressBar.dismiss();
//        super.onDestroy();
//    }

}