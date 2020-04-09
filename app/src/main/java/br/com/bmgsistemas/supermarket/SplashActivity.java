package br.com.bmgsistemas.supermarket;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

//import br.com.bmgsistemas.supermarket.core.GlobalState;
//import br.com.bmgsistemas.supermarket.core.GlobalState;

public class SplashActivity extends AppCompatActivity {

//    public GlobalState gs;
    private static final int DELAY_SHOW_MILLIS = 300;
    private final Handler mShowHandler = new Handler();
    private ProgressDialog progressBar = null;



    private static Activity activity;

//    public Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        activity = this;

//        gs = (GlobalState) this.getActivity().getApplication();


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow()
                .getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        getWindow()
                .setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            View decorView = getWindow().getDecorView();

            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

            decorView.setSystemUiVisibility(uiOptions);

            // Remember that you should never show the action bar if the
            // status bar is hidden, so hide that too if necessary.
            actionBar.hide();
        }



//        session = new Session(true);


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        ((TextView)findViewById(R.id.appname)).setTextColor(getColor(R.color.black));

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mShowHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        }, DELAY_SHOW_MILLIS);
    }


}
