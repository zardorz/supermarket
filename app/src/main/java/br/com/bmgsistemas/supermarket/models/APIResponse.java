package br.com.bmgsistemas.supermarket.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class APIResponse {

    @SerializedName("success")
    public boolean Success;

    @SerializedName("result")
    public Object Result;

    @SerializedName("errors")
    public List<Error> Errors;

    @SerializedName("statusCode")
    public int StatusCode;

    public APIResponse()
    {
        Success= false;
        Errors= new ArrayList<Error>();
        StatusCode = 200;
    }

    public void addError(Error error) {
        Errors.add(error);
    }



}
