//package br.com.bmgsistemas.supermarket.ui.product;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.os.CountDownTimer;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Locale;
//
//import br.com.bmgsistemas.supermarket.R;
//import br.com.bmgsistemas.supermarket.SplashActivity;
//import br.com.bmgsistemas.supermarket.core.Loader;
//import br.com.bmgsistemas.supermarket.enums.eServiceMethods;
//import br.com.bmgsistemas.supermarket.models.APIResponse;
//
//public class QRCodeEncoderFragment extends Fragment {
//
//    private View view;
//    private TextView tvWaitCounter;
//    private int sec ;
//    private double paymentAmount = 0;
//    private String paymentDescription  ;
//    private ImageView imgQRCode;
//    private Bitmap bitmap;
//    private AlertResult alertResult;
//    private GlobalState gs;
//    private Gson gson;
//    private boolean isDestroy = false;
//    public  String authorizationCode;
//    private static Runnable mHandlerHome;
//    private static Runnable mHandlerSplash;
//    private CountDownTimer countDownTimer;
//    private Boolean isPayment ;
//
//
//    public QRCodeEncoderFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//
//        alertResult = new AlertResult(getContext());
//
//        gs = (GlobalState) getActivity().getApplication();
//        gson = new GsonBuilder().serializeNulls().create();
//
//        mHandlerHome = new Runnable() {
//            public void run() {
//                try {
//                    HomeFragment homeFragment = new HomeFragment();
//                    getFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.fragment_container, homeFragment)
//                            .commit();
//                }catch (Exception ex) {
//                }
//            }
//        };
//
//        mHandlerSplash = new Runnable() {
//            public void run() {
//                Intent intent = new Intent(getActivity(), SplashActivity.class);
//                startActivity(intent);
//                getActivity().finish();
//            }
//        };
//
//        // Inflate the layout for this fragment
//        view = inflater.inflate(R.layout.fragment_qrcode, container, false);
//
//        tvWaitCounter = view.findViewById(R.id.tvWaitCounter);
//        TextView tvAmount = view.findViewById(R.id.tvAmount);
//
//        authorizationCode = getArguments().getString("AuthorizationCode");
//
//        paymentAmount = getArguments().getDouble("PaymentAmount");
//        isPayment = getArguments().getBoolean("IsPayment");
//
//        TextView tvDescription = view.findViewById(R.id.tvDescription);
//        paymentDescription = getArguments().getString("PaymentDescription");
//        tvDescription.setText(paymentDescription);
//
//
//        byte[] byteArray = getArguments().getByteArray("image"); //.getByteArrayExtra
//        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//
//        Locale ptBr = new Locale("pt", "BR");
//        String amount = NumberFormat.getCurrencyInstance(ptBr).format(paymentAmount);
//        tvAmount.setText(amount);
//
//        imgQRCode =view.findViewById(R.id.imgQRCode);
//        imgQRCode.setImageBitmap(bmp);
//
//        //
////        getActivity().getWindow().setSoftInputMode(
////                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//
//        InputMethodManager inputManager = (InputMethodManager) view
//                .getContext()
//                .getSystemService(Context.INPUT_METHOD_SERVICE);
//
//        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
//
//        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//
//        //
//        doShowQrCode();
//
//        return view;
//    }
//
//    private  void doShowQrCode(){
//        sec = 60;
//        imgQRCode.setVisibility(View.VISIBLE);
//
////        getActivity().getWindow().setSoftInputMode(
////                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
////
//        //removeKeypad
//
//
//        countDownTimer = new CountDownTimer(60000, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//                sec-=1;
//                tvWaitCounter.setText("Tempo Restante: " +  String.valueOf(sec) + " Seg.");
//
//                doGetAutorizationStatus();
//            }
//
//            public void onFinish() {
//
//                tvWaitCounter.setText("Tempo Expirado!");
//                imgQRCode.setVisibility(View.GONE);
//
//                doShowAlertRetry();
//            }
//        }.start();
//
////        Loader.dismiss();
//    }
//
//
//    private void doShowAlertRetry(){
//        if(getActivity() == null) {
//            // Cancel -> Send Home
//            doCancelAutorization(0);
//
//            return;
//        }
//
//        final Dialog dialog = new Dialog(getActivity());
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.activity_device_validation_automatic);
//        dialog.show();
//
//        ImageView imgStatus = dialog.findViewById(R.id.imgStatus);
//        imgStatus.setVisibility(View.GONE);
//
//        TextView messageTitle = dialog.findViewById(R.id.lblDialogMessageTitle);
//        messageTitle.setText("Tempo Expirado!");
//
//        TextView message = dialog.findViewById(R.id.lblDialogMessage);
//        message.setText("Tentar Novamente?");
//
//        LinearLayout actionsButtons = dialog.findViewById(R.id.llActionsButtons);
//        actionsButtons.setVisibility(View.VISIBLE);
//
//        TextView btnPositive = dialog.findViewById(R.id.btnPositive);
//        btnPositive.setText(" Sim ");
//        btnPositive.setVisibility(View.VISIBLE);
//        btnPositive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//
//                // Retry
//                //doShowQrCode();
//                doCancelAutorization(2);
//            }
//        });
//
//        TextView btnNegative = dialog.findViewById(R.id.btnNegative);
//        btnNegative.setText(" Não ");
//        btnNegative.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//
//                // Cancel -> Send Home
//                doCancelAutorization(0);
//
//            }
//        });
//
//        TextView btnConfirmed = dialog.findViewById(R.id.btnConfirmed);
//        btnConfirmed.setText("        Transferir ");
//        btnConfirmed.setVisibility(View.VISIBLE);
//
//        btnConfirmed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Go Transfer
//                dialog.dismiss();
//
//                doCancelAutorization(1);
//            }
//        });
//
//    }
//
//
//    private void doCancelAutorization(final int actionResult ){
//
//
//
//        if(getActivity()!= null && !isDestroy )
//            Loader.show(getActivity());
//
//        HashMap<String, Object> postParameters = new HashMap<String, Object>();
//
//        postParameters.put("AuthorizationCode", authorizationCode);
//
//        new HTTPClientRequest(getActivity(), eServiceMethods.CANCEL_AUTHORIZATION) {
//            protected void onPostExecute(APIResponse apiResponse) {
//
//                if(getActivity()== null)
//                    return;
//
//                Loader.dismiss();
//
//                if (apiResponse != null && apiResponse.Success) {
//                    //Retorna
//
//                    countDownTimer.cancel();
//
////                  String json = gson.toJson(apiResponse.Result);
////                  session.setActualBalance(gson.fromJson(json, Float.class));
//                    if(actionResult == 0) {
//                        // 0 - Go Home
//                        showMessage(getActivity(), "", "Operação Cancelada!", null);
//
//                        HomeFragment homeFragment  = new HomeFragment();
//                        getFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.fragment_container, homeFragment )
//                                .commitAllowingStateLoss();
//                    }else if(actionResult == 1) {
//
//                        // 1 - Redireciona para transferencia
//                        Bundle bundle = new Bundle();
//                        bundle.putDouble("Amount", paymentAmount);
//
//                        SendMoneyFragment sendMoneyFragment  = new SendMoneyFragment();
//                        sendMoneyFragment.setArguments(bundle);
//
//                        getFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.fragment_container, sendMoneyFragment )
//                                .commit();
//                    }else if(actionResult == 2) {
//
//                        // 2 - Redireciona para Inicio dopagamento
//                        Bundle bundle = new Bundle();
//                        bundle.putDouble("PaymentAmount", paymentAmount);
//                        bundle.putBoolean("IsPayment", true);
//                        bundle.putString("PaymentDescription", paymentDescription);
//
//                        SendPaymentFragment sendPaymentFragment  = new SendPaymentFragment();
//                        sendPaymentFragment.setArguments(bundle);
//
//                        getFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.fragment_container, sendPaymentFragment )
//                                .commit();
//                    }
//
//                } else {
//
//                    List<Error> errors = apiResponse.Errors;
//                    String msg = "";
//                    String json = gson.toJson(apiResponse.Result);
//
//                    for (Error item : errors) {
//                        msg = "(" + String.valueOf(item.getCode()) + ") " + item.getMessageTranslated();
//
////                        if (item.getCode() == -2040 || item.getCode() == -4010) {
////                            session.setRememberMe(false);
////                        }
//
//                        if(item.getCode() == -9410){
//                            // 401 -> Redireciona para o login
//                            showMessage(getActivity(), "", "Conta cancelada!", mHandlerSplash);
//                            return;
//                        }
//                    }
//
//                    if(getActivity()!= null)
//                        showMessage(getActivity(), "", msg, null);
//
//                }
//            }
//        }.execute(postParameters);
//
//
//    }
//
//
//
//    private void doGetAutorizationStatus(){
//
////        Loader.show(getActivity());
//
//        HashMap<String, Object> postParameters = new HashMap<String, Object>();
//
//        postParameters.put("AuthorizationCode", authorizationCode);
//
//        new HTTPClientRequest(getActivity(), eServiceMethods.TRANSACTION_STATUS) {
//            protected void onPostExecute(APIResponse apiResponse) {
////                Loader.dismiss();
//
//                if (apiResponse != null && apiResponse.Success) {
//                    //Retorna
//
//                    String json = gson.toJson(apiResponse.Result);
//                    Transaction transaction = gson.fromJson(json, Transaction.class);
//
//                    if (transaction.StatusTransaction == eStatusTransactionOperation.PENDING) {
//
//                    } else if (transaction.StatusTransaction == eStatusTransactionOperation.APROVED) {
//                        countDownTimer.cancel();
//
//                        if(getActivity()!= null)
//                            showMessageSucess(getActivity(), "", "Operação realizada com sucesso!", mHandlerHome);
//
//                    } else if (transaction.StatusTransaction == eStatusTransactionOperation.EXPIRED) {
//                        countDownTimer.cancel();
//
//                        if(getActivity()!= null)
//                            showMessage(getActivity(), "", "Operação expirada!", mHandlerHome);
//
//                    } else if (transaction.StatusTransaction == eStatusTransactionOperation.CANCELED) {
//                        countDownTimer.cancel();
//
//                        if(getActivity()!= null)
//                            showMessage(getActivity(), "", "Operação cancelada!", mHandlerHome);
//
//                    }
//
//
//                } else {
//
//                    List<Error> errors = apiResponse.Errors;
//                    String msg = "";
//                    String json = gson.toJson(apiResponse.Result);
//
//                    for (Error item : errors) {
//                        msg = "(" + String.valueOf(item.getCode()) + ") " + item.getMessageTranslated();
//
////                        if (item.getCode() == -2040 || item.getCode() == -4010) {
////                            session.setRememberMe(false);
////                        }
//
//                        if(item.getCode() == -9410){
//                            // 401 -> Redireciona para o login
//                            showMessage(getActivity(), "", "Conta cancelada!", mHandlerSplash);
//                            return;
//                        }
//                    }
//
//                    if(getActivity()!= null)
//                        showMessage(getActivity(), "", msg, null);
//
//                }
//            }
//        }.execute(postParameters);
//
//
//    }
//
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        isDestroy = true;
//        countDownTimer.cancel();
////        doCancelAutorization(0);
//    }
//
//    //    @Override
////    public void onStop() {
////        super.onStop();
//////        countDownTimer.cancel();
//////        doCancelAutorization(0);
////    }
////
////    @Override
////    public void onDestroy() {
////        super.onDestroy();
//////        countDownTimer.cancel();
//////        doCancelAutorization(0);
////    }
////
////
//    @Override
//    public void onResume() {
//        super.onResume();
//
//        if(isDestroy){
//
//            isDestroy = false;
//
////            showMessage(getActivity(), "", "Operação Cancelada!", null);
//            HomeFragment homeFragment  = new HomeFragment();
//            getFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, homeFragment )
//                    .commitAllowingStateLoss();
//        }
//
//    }
//
//
//}
