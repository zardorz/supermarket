package br.com.bmgsistemas.supermarket.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authorization {

    @SerializedName("sessionId")
    @Expose
    private String sessionId;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("customerName")
    @Expose
    private String customerName;

    @SerializedName("accessToken")
    @Expose
    private String accessToken;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
