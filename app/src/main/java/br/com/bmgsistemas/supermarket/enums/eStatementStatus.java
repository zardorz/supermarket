package br.com.bmgsistemas.supermarket.enums;


import com.google.gson.annotations.SerializedName;

public enum eStatementStatus  {
    @SerializedName("1.0")
    OPEN(1),

    @SerializedName("2.0")
    CONCILED(2),

    @SerializedName("3.0")
    RECONCILED(3),

    @SerializedName("4.0")
    FILE_IMPORT(4);

    eStatementStatus (int i)
    {
        this.type = i;
    }

    private int type;

    public int getNumericType()
    {
        return type;
    }

    public static eStatementStatus fromInteger(int x) {
        switch(x) {
            case 1:
                return OPEN;
            case 2:
                return CONCILED;
            case 3:
                return RECONCILED;
            case 4:
                return FILE_IMPORT;

        }
        return null;
    }

}