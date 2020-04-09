package br.com.bmgsistemas.supermarket.enums;

import com.google.gson.annotations.SerializedName;

public enum eStatementType {
    @SerializedName("1.0")
    NONE(1),

    @SerializedName("2.0")
    DEBIT(2),

    @SerializedName("3.0")
    CREDIT(3),

    @SerializedName("4.0")
    TRANSFERENCY(4);

    eStatementType (int i)
    {
        this.type = i;
    }

    private int type;

    public int getNumericType()
    {
        return type;
    }

    public static eStatementType fromInteger(int x) {
        switch(x) {
            case 1:
                return NONE;
            case 2:
                return DEBIT;
            case 3:
                return CREDIT;
            case 4:
                return TRANSFERENCY;

        }
        return null;
    }
}

