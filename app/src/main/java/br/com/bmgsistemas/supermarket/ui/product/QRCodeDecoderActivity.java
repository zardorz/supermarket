//package br.com.bmgsistemas.supermarket.ui.product;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.PointF;
//import android.os.Bundle;
//import android.support.v4.app.ActivityCompat;
//import android.view.ViewGroup;
//import android.widget.CheckBox;
//import android.widget.TextView;
//
//import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
//import com.dlazaro66.qrcodereaderview.QRCodeReaderView.OnQRCodeReadListener;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import java.util.HashMap;
//import java.util.List;
//
//import br.com.bmgsistemas.supermarket.R;
//import br.com.bmgsistemas.supermarket.core.PointsOverlayView;
//import br.com.bmgsistemas.supermarket.enums.eServiceMethods;
//import br.com.bmgsistemas.supermarket.models.APIResponse;
//
//public class QRCodeDecoderActivity extends Activity
//        implements ActivityCompat.OnRequestPermissionsResultCallback,
//        OnQRCodeReadListener {
//
////    private static final int MY_PERMISSION_REQUEST_CAMERA = 0;
//
//    private ViewGroup mainLayout;
//    private static Runnable mHandlerHome;
//    private TextView resultTextView;
//    private QRCodeReaderView qrCodeReaderView;
//    private CheckBox flashlightCheckBox;
//    private CheckBox enableDecodingCheckBox;
//    private PointsOverlayView pointsOverlayView;
//    private Activity activity;
////    private GlobalState gs;
//    private Gson gson;
////    private AlertResult alertResult;
//    private boolean isReading;
//
//    @Override protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
////        setContentView(R.layout.activity_decoder);
//
////        mainLayout = (ViewGroup) findViewById(R.id.main_layout);
//
//        setContentView(R.layout.content_decoder);
//
//        activity = this;// (Activity)getBaseContext();
////        alertResult = new AlertResult(activity);
//
//        isReading = false;
//
////        gs = (GlobalState) getApplication();// activity.getApplication();
//        gson = new GsonBuilder().serializeNulls().create();
//
////        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//        initQRCodeReaderView();
////        } else {
////            requestCameraPermission();
////        }
//
//        mHandlerHome = new Runnable() {
//            public void run() {
//                isReading = true;
//
////                Silvio
////                doProcessQRCode("");
//
//                if (qrCodeReaderView != null)
//                    qrCodeReaderView.stopCamera();
//            }
//        };
//
//    }
//
//    @Override protected void onResume() {
//        super.onResume();
//
//        if (qrCodeReaderView != null) {
//            qrCodeReaderView.startCamera();
//        }
//    }
//
//    @Override protected void onPause() {
//        super.onPause();
//
//        if (qrCodeReaderView != null) {
//            qrCodeReaderView.stopCamera();
//        }
//    }
//
////    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
////        if (requestCode != MY_PERMISSION_REQUEST_CAMERA) {
////            return;
////        }
////
////        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//////            Snackbar.make(mainLayout, "Camera permission was granted.", Snackbar.LENGTH_SHORT).show();
////            initQRCodeReaderView();
////        } else {
//////            Snackbar.make(mainLayout, "Camera permission request was denied.", Snackbar.LENGTH_SHORT).show();
////            showMessage(activity, "", "Sem permissão para acessar a camera!", mHandlerHome);
////        }
////    }
//
//
//
//    // Called when a QR is decoded
//    // "text" : the text encoded in QR
//    // "points" : points where QR control points are placed
//    @Override public void onQRCodeRead(String text, PointF[] points) {
//        resultTextView.setText(text);
////        pointsOverlayView.setPoints(points);
//
//        if(text != null && !isReading) {
//            isReading = true;
////            Silvio
////            doProcessQRCode(text);
//
//            qrCodeReaderView.stopCamera();
//        }
//
//    }
//
////    private void requestCameraPermission() {
////        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
//////            Snackbar.make(mainLayout, "Camera access is required to display the camera preview.", Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
//////                @Override public void onClick(View view) {
////                    ActivityCompat.requestPermissions(QRCodeDecoderActivity.this, new String[] {
////                            Manifest.permission.CAMERA
////                    }, MY_PERMISSION_REQUEST_CAMERA);
//////                }
//////            }).show();
////        } else {
//////            Snackbar.make(mainLayout, "Permission is not available. Requesting camera permission.", Snackbar.LENGTH_SHORT).show();
////            ActivityCompat.requestPermissions(this, new String[] {
////                    Manifest.permission.CAMERA
////            }, MY_PERMISSION_REQUEST_CAMERA);
////        }
////    }
//
//    private void initQRCodeReaderView() {
////        View content = getLayoutInflater().inflate(R.layout.content_decoder, mainLayout, true);
//
//        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
//        resultTextView = (TextView) findViewById(R.id.result_text_view);
//        resultTextView.setText("Aproxime o leitor do código para receber o troco");
//
//        qrCodeReaderView.setOnQRCodeReadListener(this);
//        qrCodeReaderView.forceAutoFocus();
////        qrCodeReaderView.setQRDecodingEnabled(true);
//        qrCodeReaderView.setBackCamera();
//        qrCodeReaderView.setAutofocusInterval(1000);
//
//        qrCodeReaderView.startCamera();
//    }
//
//
////    private void doProcessQRCode(String authorizationCode ){
//////        Loader.show(activity);
////
////        HashMap<String, Object> postParameters = new HashMap<String, Object>();
////
////        postParameters.put("AuthorizationCode", authorizationCode);
////
////        new HTTPClientRequest(activity, eServiceMethods.GET_CHANGE_PAYMENT) {
////            protected void onPostExecute(APIResponse apiResponse) {
//////                Loader.dismiss();
////
////                if (apiResponse != null && apiResponse.Success) {
////                    //Retorna
////
////                    String json = gson.toJson(apiResponse.Result);
////                    Float actualBalance = gson.fromJson(json, Float.class);
////                    session.setActualBalance(actualBalance);
////
//////                    showMessage(activity, "", "Operação realizada com sucesso!", mHandlerHome);
////
////                    Intent resultIntent = new Intent();
////                    resultIntent.putExtra("RETURN_MSG", "Operação realizada com sucesso!");
////                    setResult(Activity.RESULT_OK, resultIntent);
////
////                    finish();
////
//////                    isReading = false;
////                } else {
////
////                    List<Error> errors = apiResponse.Errors;
////                    String msg = "";
////                    String json = gson.toJson(apiResponse.Result);
////
////                    for (Error item : errors) {
////                        msg = "(" + String.valueOf(item.getCode()) + ") " + item.getMessageTranslated();
////
//////                        if (item.getCode() == -2040 || item.getCode() == -4010) {
//////                            session.setRememberMe(false);
//////                        }
////                    }
////
//////                    showMessage(activity, "", msg, null);
////
////                    Intent resultIntent = new Intent();
////                    resultIntent.putExtra("RETURN_MSG", msg);
////                    setResult(Activity.RESULT_CANCELED, resultIntent);
////
////                    finish();
////
//////                    isReading = false;
////
////                }
////            }
////        }.execute(postParameters);
////
////
////    }
//}
