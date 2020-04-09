package br.com.bmgsistemas.supermarket.core;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.bmgsistemas.supermarket.models.Authorization;

public class SessionManager {
    static SharedPreferences pref ; //= getContext().getSharedPreferences("MyBucksPref", 0); // 0 - for private mode
    //static SharedPreferences.Editor editor = pref.edit();

    private static GlobalState mContext;

    private String TOKEN_ID = "TokenID";
    private String AUTHORIZATION = "Authorization";
    private boolean isDevelopment = false;
    private boolean isDebugMode = false;

    public SessionManager( GlobalState mContext, boolean isDevelopment,  boolean isDebugMode){
        this.isDevelopment = isDevelopment;
        this.isDebugMode = isDebugMode;

        pref = mContext.getSharedPreferences("MyBucksPref", 0); // 0 - for private mode
    }


    public boolean getIsDevelopment(){
        return isDevelopment;
    }

    public boolean getIsDebugMode(){
        return isDebugMode;
    }

//    public String getTokenID(){
//        return pref.getString(TOKEN_ID,"");
//    }
//
//    public void setTokenID(String tokenID){
//        pref.edit().putString(TOKEN_ID, tokenID ).commit();
//    }


    public Authorization getAuthorization(){
        Gson gson = new GsonBuilder().serializeNulls().create();

        String json = pref.getString(AUTHORIZATION,"");

        return gson.fromJson(json, Authorization.class);
    }

    public void setAuthorization(Authorization authorization){
        Gson gson = new GsonBuilder().serializeNulls().create();

        String json = gson.toJson(authorization);

        pref.edit().putString(AUTHORIZATION, json ).commit();
    }
}

