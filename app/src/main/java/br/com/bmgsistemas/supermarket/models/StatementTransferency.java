package br.com.bmgsistemas.supermarket.models;

import com.google.gson.annotations.SerializedName;

public class StatementTransferency {
    @SerializedName("id")
    public String id;

    @SerializedName("statementFromID")
    public String statementFromID;

    @SerializedName("financeAccountFromID")
    public int financeAccountFromID ;

    @SerializedName("statementToID")
    public String statementToID;

    @SerializedName("financeAccountToID")
    public int financeAccountToID ;
}
