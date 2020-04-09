package br.com.bmgsistemas.supermarket.ui.product;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;

import br.com.bmgsistemas.supermarket.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class EANDecoderActivity extends AppCompatActivity
        implements ZXingScannerView.ResultHandler {

    private static final int EAN_REQUEST = 1;  // The request code
    private ZXingScannerView mScannerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ean_decoder);

//        setupToolbar();

        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);
    }

    private void setScannerProperties(ZXingScannerView mScannerView) {
//        mScannerView.setFormats(listOf(BarcodeFormat.QR_CODE));
        mScannerView.setAutoFocus(false);
        mScannerView.setLaserColor(R.color.colorAccent);
        mScannerView.setMaskColor(R.color.colorAccent);
        if (Build.MANUFACTURER.equalsIgnoreCase ("HUAWEI"))
            mScannerView.setAspectTolerance(0.5f);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {

//        super.onActivityResult(EAN_REQUEST,  Activity.RESULT_OK, rawResult);

//        Toast.makeText(this, "Contents = " + rawResult.getText() +
//                ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();

//        // Note:
//        // * Wait 2 seconds to resume the preview.
//        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
//        // * I don't know why this is the case but I don't have the time to figure out.
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mScannerView.resumeCameraPreview(EANDecoderActivity.this);
//            }
//        }, 2000);

//        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
//        // handle scan result
//        if (scanResult != null) {
//            FragmentManager fm = getSupportFragmentManager();
//
//            Fragment newFrame = MainFragment.newInstance(scanResult.toString());
//
//            fm.beginTransaction().replace(R.id.fragmentContainer, newFrame).commit();
//        }

        mScannerView.removeAllViews(); //<- here remove all the views, it will make an Activity having no View
        mScannerView.stopCamera(); //<- then stop the camera
//        setContentView(R.layout.activity_Main); //<- and set the View again.
//        final String vString = result.getText();

        Intent intentMessage = new Intent();

        // put the message in Intent
        intentMessage.putExtra("RETURN_MSG", rawResult.getText());
        // Set The Result in Intent
        setResult(RESULT_OK, intentMessage);
        // finish The activity
        finish();
    }
}
