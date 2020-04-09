package br.com.bmgsistemas.supermarket.models;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class CartProduct implements Serializable {

    public String ProductID ;

    public String CartID ;

    public String ShoppingID ;

    public String ProductBarCode ;

    public Integer ProductQtd ;

    public float ProductAmount ;

    public float ProductTotalAmount ;

    public String ProductName;

    public String ProductBrandID;

    public String ProductCategoryID;

    public String ProductManufacturerID;

    public String ProductNCM;


    public  String getProductAmountFormated (){
        Locale ptBr = new Locale("pt", "BR");
        NumberFormat f = NumberFormat.getNumberInstance(ptBr);
        f.setMinimumFractionDigits(2);
        return f.format(ProductAmount);
    }


    public  String getProductTotalAmountFormated (){
        Locale ptBr = new Locale("pt", "BR");
        NumberFormat f = NumberFormat.getNumberInstance(ptBr);
        f.setMinimumFractionDigits(2);
        return f.format(ProductTotalAmount);
    }
}
