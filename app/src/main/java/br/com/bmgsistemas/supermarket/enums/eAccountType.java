package br.com.bmgsistemas.supermarket.enums;

import com.google.gson.annotations.SerializedName;

import br.com.bmgsistemas.supermarket.R;

public enum eAccountType {
    @SerializedName("1.0")
    WALLET(1),

    @SerializedName("2.0")
    SAVE(2),

    @SerializedName("3.0")
    CHECKING_ACCOUNT(3),

    @SerializedName("4.0")
    CREDIT_CARD(4);

    eAccountType (int i)
    {
        this.type = i;
    }

    private int type;

    public int getNumericType()
    {
        return type;
    }

    public static eAccountType fromInteger(int x) {
        switch(x) {
            case 1:
                return WALLET;
            case 2:
                return SAVE;
            case 3:
                return CHECKING_ACCOUNT;
            case 4:
                return CREDIT_CARD;

        }
        return null;
    }

    public static int getIcon(eAccountType accountType) {
        switch(accountType) {
            case WALLET:
                return R.drawable.ic_account_balance_wallet_black;

            case SAVE:
                return R.drawable.ic_save_black_24dp;

            case CHECKING_ACCOUNT:
                return R.drawable.ic_account_balance_black;

            case CREDIT_CARD:
                return R.drawable.ic_credit_card_black;

        }
        return 0;
    }

    public static String getDescription(eAccountType accountType) {
        switch(accountType) {
            case WALLET:
                return "Carteira";

            case SAVE:
                return "Poupança";

            case CHECKING_ACCOUNT:
                return "Conta Corrente";

            case CREDIT_CARD:
                return "Cartão de Crédito";

        }
        return "";
    }
}
