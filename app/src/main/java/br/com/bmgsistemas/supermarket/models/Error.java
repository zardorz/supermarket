package br.com.bmgsistemas.supermarket.models;

import com.google.gson.annotations.SerializedName;

public class Error {
    @SerializedName("code")
    private int code ;

    @SerializedName("message")
    private String message ;

    @SerializedName("obs")
    private String observation ;

    @SerializedName("stackTrace")
    private String stackTrace;

    public Error(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public Error(int code, String message, String observation)
    {
        this.code = code;
        this.message = message;
        this.observation = observation;
    }
//    public Error(int code, String messageTranslated)
//    {
//        this.code = code;
//        this.messageTranslated = messageTranslated;
//    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

//    public String getMessageTranslated() {
//        return messageTranslated;
//    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public void setMessageTranslated(String messageTranslated) {
//        this.messageTranslated = messageTranslated;
//    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

}
