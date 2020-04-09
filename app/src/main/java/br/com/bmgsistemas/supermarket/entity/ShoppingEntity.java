package br.com.bmgsistemas.supermarket.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.bmgsistemas.supermarket.enums.eShoppingStatus;


@Entity
public class ShoppingEntity implements Serializable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "ID")
    public String ID ;

    @ColumnInfo(name = "StoreID")
    public String StoreID ;

//    @ColumnInfo(name = "StoreName")
//    public String StoreName ;

    @ColumnInfo(name = "Name")
    public String Name ;

    @ColumnInfo(name = "BuyDate")
    public String BuyDate ;

    @ColumnInfo(name = "Status")
    public int Status = eShoppingStatus.OPEN.getNumericType();

    @ColumnInfo(name = "Qtd")
    public Integer QtdItens = 0;

    @ColumnInfo(name = "TotalAmount")
    public float TotalAmount = 0.00f;

    @ColumnInfo(name = "TotalDiscount")
    public float TotalDiscount = 0.00f;

    @ColumnInfo(name = "TotalPaid")
    public float TotalPaid = 0.00f;


//    @ColumnInfo(name = "Paid")
//    public Boolean Paid = false;


    public  String getTotalDiscountFormated (){
        Locale ptBr = new Locale("pt", "BR");
        NumberFormat f = NumberFormat.getNumberInstance(ptBr);
        f.setMinimumFractionDigits(2);
        return f.format(TotalDiscount);
    }

    public  String getTotalPaidFormated (){
        Locale ptBr = new Locale("pt", "BR");
        NumberFormat f = NumberFormat.getNumberInstance(ptBr);
        f.setMinimumFractionDigits(2);
        return f.format(TotalPaid);
    }

//    public String getTotalAmountFormated(){
//        Locale ptBr = new Locale("pt", "BR");
//        String totalAmountFormated = NumberFormat.getCurrencyInstance(ptBr).format(TotalAmount);
//        return totalAmountFormated ;
//    }


    public String getBuyDateFormated() {
        //SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        String buyDateFormated = "";

        if (BuyDate != null) {
            try {

                Date initDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss a").parse(BuyDate);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                buyDateFormated = sdf.format(initDate);
            } catch (Exception e) {
                //java.text.ParseException: Unparseable date: Geting erroB
                System.out.println("Excep" + e);
            }

        }

        return buyDateFormated;
    }



}