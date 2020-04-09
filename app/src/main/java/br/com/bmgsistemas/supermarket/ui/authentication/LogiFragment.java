package br.com.bmgsistemas.supermarket.ui.authentication;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import br.com.bmgsistemas.supermarket.MainActivity;
import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.core.GlobalState;
import br.com.bmgsistemas.supermarket.core.SessionManager;
import br.com.bmgsistemas.supermarket.enums.eServiceMethods;
import br.com.bmgsistemas.supermarket.helpers.HTTPRequest;
import br.com.bmgsistemas.supermarket.models.APIResponse;
import br.com.bmgsistemas.supermarket.models.Authorization;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogiFragment extends Fragment {

    private View view;
    private static SessionManager session ;

    public LogiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_logi, container, false);

        session = GlobalState.getSession();

        ((TextView) view.findViewById(R.id.hypForgotPassword)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Intent intent = new Intent(activity, LostPasswordActivity.class);
//                activity.startActivity(intent);
            }
        });


        ((TextView) view.findViewById(R.id.hypCreateAccount)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Intent intent = new Intent(activity, CreateAccountActivity.class);
//                activity.startActivity(intent);
            }
        });


        ((TextView) view.findViewById(R.id.lblPageTitle)).setText(getResources().getString(R.string.login_title));
        ((TextView) view.findViewById(R.id.lblPageTitleDescription)).setText(getResources().getString(R.string.login_title_description));


        ((Button) view.findViewById(R.id.btnLogin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isValidFields();

////                Intent intent = new Intent(activity, DecoderActivity.class);
////                startActivity(intent);
//
//                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION);
//                } else {
//                    //Silvio
////                    Intent intent = new Intent(activity, DecoderActivity.class);
////                    startActivity(intent);
//////                    intent.initiateScan();
////
//////                    Intent it = new Intent(activity, com.google.zxing.client.android.CaptureActivity.class);
//////                    startActivityForResult(it, 0);
//                }
            }

        });

        return view;

    }

//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
//        if (scanResult != null) {
//            // handle scan result
//        }
//        // else continue with any other code you need in the method
//
//    }

    private void isValidFields() {

        EditText edEmail = view.findViewById(R.id.edEmail);
        EditText edPassword = view.findViewById(R.id.edPassword);

        edEmail.setText("zardorz@hotmail.com");
        edPassword.setText("123456");

        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            edEmail.setError("Campo Obrigatório!");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            edPassword.setError("Campo Obrigatório!");
            return;
        }


        HashMap<String, Object> postParameters = new HashMap<String, Object>();

        postParameters.put("Email", email);
        postParameters.put("Password", password);

        doLogin(postParameters);

    }


    private void doLogin( HashMap<String, Object> postParameters){
//        Loader.show(activity);


        new HTTPRequest(getActivity(), eServiceMethods.USER_ACCOUNT_AUTHENTICATION) {
            protected  void onPostExecute(APIResponse apiResponse) {
                onResultRequest(apiResponse);
            }
        }.execute(postParameters);
    }

    private   void onResultRequest(APIResponse apiResponse){
        Gson gson = new GsonBuilder().serializeNulls().create();

//        Loader.dismiss();
        if (apiResponse != null && apiResponse.Success) {
            // Se ocorreu sucesso código foi validado


            String json = gson.toJson(apiResponse.Result);
            Authorization authorization = gson.fromJson(json, Authorization.class);
            //session.setTokenID(authorization.getResult().getAccessToken());
            session.setAuthorization(authorization );

//            silvio
            Intent intent = new Intent(getActivity().getApplicationContext() , MainActivity.class);
            startActivity(intent);
//            finish();

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

//    private static final int ZXING_CAMERA_PERMISSION = 1;
//    @Override
//    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case ZXING_CAMERA_PERMISSION:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                    if(mClss != null) {
//                    Intent intent = new Intent(this, EncoderActivity.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(this, "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
//                }
//                return;
//        }
//    }

}
