package br.com.bmgsistemas.supermarket.core;


import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class GlobalState extends Application {

    private static GlobalState app;

    private static SessionManager mSession;
    private static AppDatabase db;

    private String KEY_DEMO_NAME = "Demo Name";
//    private boolean isDevelopment = false;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        mSession = new SessionManager(app,false, false);

        db = Room.databaseBuilder(app.getApplicationContext(),
                AppDatabase.class, "database-supermarket")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static GlobalState getContext() {
        return app;
    }

    public static SessionManager getSession(){
        return mSession;
    }

    public static AppDatabase getDB(){
        return db;
    }

    public static boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public String getVersion(){
        String version = "0.0.0";

        try {
            PackageInfo pInfo = app.getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }


}