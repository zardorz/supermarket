package br.com.bmgsistemas.supermarket.enums;

import com.google.gson.annotations.SerializedName;

public enum eShoppingStatus {
    @SerializedName("0.0")
    ALL(0),

    @SerializedName("1.0")
    OPEN(1),

    @SerializedName("2.0")
    PAID(2),

    @SerializedName("3.0")
    CANCELED(3);


    eShoppingStatus(int i) {
        this.type = i;
    }

    private int type;

    public int getNumericType() {
        return type;
    }

    public static eShoppingStatus fromInteger(int x) {
        switch (x) {
            case 0:
                return ALL;
            case 1:
                return OPEN;
            case 2:
                return PAID;
            case 3:
                return CANCELED;
        }
        return null;
    }

    public static String getDescription(eShoppingStatus shoppingStatus) {
        switch(shoppingStatus) {
            case ALL:
                return "Todas";

            case OPEN:
                return "Aberta";

            case PAID:
                return "Fechada";

            case CANCELED:
                return "Cancelada";

        }
        return "";
    }

    public static String getDescription(int status) {

        return eShoppingStatus.getDescription(eShoppingStatus.fromInteger(status));
    }
}


