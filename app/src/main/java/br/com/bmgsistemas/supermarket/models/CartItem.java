package br.com.bmgsistemas.supermarket.models;

import android.arch.persistence.room.ColumnInfo;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartItem {

    public String   productBarCode ;
    public String   productName ;
    public Integer  productQtd ;
    public float    productAmount ;
    public float    productTotalAmount ;

    public CartItem(String   productBarCode, String   productName, Integer  productQtd, float    productAmount, float productTotalAmount){
        this.productBarCode = productBarCode ;
        this.productName    = productName    ;
        this.productQtd     = productQtd     ;
        this.productAmount  = productAmount  ;
        this.productTotalAmount = productTotalAmount;
    }

    public String getProductBarCode(){
        return productBarCode;
    }
    public String getProductName(){
        return productName;
    }
    public Integer getProductQtd(){
        return productQtd;
    }
    public float getProductAmount(){
        return productAmount;
    }

    public float getProductTotalAmount(){
        return productTotalAmount;
    }

    public String getProductAmountFormated() {
        Locale ptBr = new Locale("pt", "BR");

        NumberFormat f = NumberFormat.getCurrencyInstance(ptBr);
                f.setMinimumFractionDigits(2);

        String productAmountFormated = f.format(productAmount);
        return productAmountFormated;
    }

    public String getProductTotalAmountFormated() {
        Locale ptBr = new Locale("pt", "BR");

        NumberFormat f = NumberFormat.getCurrencyInstance(ptBr);
        f.setMinimumFractionDigits(2);

        String productAmountFormated = f.format(productTotalAmount);
        return productAmountFormated;
    }
}
