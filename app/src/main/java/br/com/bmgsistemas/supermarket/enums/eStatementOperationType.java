package br.com.bmgsistemas.supermarket.enums;


import com.google.gson.annotations.SerializedName;

public enum eStatementOperationType {

    @SerializedName("1.0")
    DEBIT(1),

    @SerializedName("2.0")
    CREDIT(2);

    eStatementOperationType (int i)
    {
        this.type = i;
    }

    private int type;

    public int getNumericType()
    {
        return type;
    }

    public static eStatementOperationType fromInteger(int x) {
        switch(x) {
            case 1:
                return DEBIT;
            case 2:
                return CREDIT;

        }
        return null;
    }
}