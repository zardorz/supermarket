package br.com.bmgsistemas.supermarket.enums;

import com.google.gson.annotations.SerializedName;

public enum eProductListItemClick {

    @SerializedName("1.0")
    PRODUCT_SATISFACTION(1),

    @SerializedName("2.0")
    PRODUCT_LASTBUYDATE(2),

    @SerializedName("3.0")
    PRODUCT_MAXPRICE(3),

    @SerializedName("4.0")
    PRODUCT_AVERAGEPRICE(4),

    @SerializedName("5.0")
    PRODUCT_QTDINVENTORY(5),

    @SerializedName("6.0")
    PRODUCT_DETAIL(6),

    @SerializedName("7.0")
    EDIT(7),

    @SerializedName("8.0")
    DELETE(8);

    eProductListItemClick(int i) {
        this.type = i;
    }

    private int type;

    public int getNumericType() {
        return type;
    }

    public static eProductListItemClick fromInteger(int x) {
        switch (x) {
            case 1:
                return PRODUCT_SATISFACTION;
            case 2:
                return PRODUCT_LASTBUYDATE;
            case 3:
                return PRODUCT_MAXPRICE;
            case 4:
                return PRODUCT_AVERAGEPRICE;
            case 5:
                return PRODUCT_QTDINVENTORY;
            case 6:
                return PRODUCT_DETAIL;

            case 7:
                return EDIT;

            case 8:
                return DELETE;
        }
        return null;
    }
}