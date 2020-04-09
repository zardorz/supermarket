package br.com.bmgsistemas.supermarket.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {
    //region Fields
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("balance")
    @Expose
    private float balance;

    @SerializedName("balanceForecast")
    @Expose
    private float balanceForecast;


    @SerializedName("instituitionID")
    @Expose
    private Integer instituitionID;
    @SerializedName("instituitionName")
    @Expose
    private Object instituitionName;
    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("typeID")
    @Expose
    private Integer typeID;
    @SerializedName("typeName")
    @Expose
    private String typeName;
    @SerializedName("isFavorite")
    @Expose
    private Boolean isFavorite;
    @SerializedName("isCashFlow")
    @Expose
    private Boolean isCashFlow;
    @SerializedName("isInactive")
    @Expose
    private Boolean isInactive;
    //endregion

    //region GET/SET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInstituitionID() {
        return instituitionID;
    }

    public void setInstituitionID(Integer instituitionID) {
        this.instituitionID = instituitionID;
    }

    public Object getInstituitionName() {
        return instituitionName;
    }

    public void setInstituitionName(Object instituitionName) {
        this.instituitionName = instituitionName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Boolean getIsCashFlow() {
        return isCashFlow;
    }

    public void setIsCashFlow(Boolean isCashFlow) {
        this.isCashFlow = isCashFlow;
    }

    public Boolean getIsInactive() {
        return isInactive;
    }

    public void setIsInactive(Boolean isInactive) {
        this.isInactive = isInactive;
    }
    //endregion


    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getBalanceFormated() {
        return "9.999.999,99";
    }

    public float getBalanceForecast() {
        return balanceForecast;
    }

    public void setBalanceForecast(float balanceForecast) {
        this.balanceForecast = balanceForecast;
    }

    public String getBalanceForecastFormated() {
        return "9.999.999,99";
    }

}
