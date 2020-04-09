package br.com.bmgsistemas.supermarket.models;

import android.arch.persistence.room.Ignore;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.bmgsistemas.supermarket.enums.eShoppingStatus;

public class Shopping {
//    private String name;
//    private eShoppingStatus status = eShoppingStatus.OPEN;
//    private String buyDate;
//    private Integer qtdItens = 0;
//    private Float totalPaid = 0.00f;
//    private String storeName;


    private String id ;
    private String storeID ;
    private String storeName ;
    private String name ;
    private String buyDate ;
    private Integer status = eShoppingStatus.OPEN.getNumericType();
    private Integer qtdItens = 0;
    private float totalAmount = 0.00f;
    private float totalDiscount = 0.00f;
    private float totalPaid = 0.00f;

    public Shopping(){
//        this.status = eShoppingStatus.OPEN;
    }

    @Ignore
    public Shopping(String name, String buyDate) {
        this.name = name;
        this.buyDate = buyDate;
    }

//    public Shopping(ShoppingEntity shopping) {
//        this.name = shopping.Name;
//        this.status = eShoppingStatus.fromInteger(shopping.Status);
//        this.buyDate = shopping.BuyDate;
//        this.qtdItens = shopping.QtdItens;
//        this.totalPaid = shopping.TotalPaid;
//        this.storeName = shopping.StoreName;
//    }

    @Ignore
    public Shopping(String shoppingName,String shoppingBuyDate, Integer shoppingQtdItens, Float shoppingTotalPaid, String shoppingStoreName, Integer shoppingStatus ) {
        this.name = shoppingName;
        this.status = shoppingStatus;
        this.buyDate = shoppingBuyDate;
        this.qtdItens = shoppingQtdItens;
        this.setTotalPaid(shoppingTotalPaid);
        this.storeName = shoppingStoreName;
    }

    public String getId(){
        return id;
    }
    public  void setId(String id ){
        this.id= id;
    }

    public String getStoreID(){
        return storeID;
    }
    public  void setStoreID(String storeID ){
        this.storeID= storeID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }


    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public eShoppingStatus getStatusToEnum() {
        return eShoppingStatus.fromInteger(status);
    }
    public void setStatusFrom(eShoppingStatus status) {
        this.status = status.getNumericType();
    }

//    public String getStatus() {
//        if (paid)
//            return "Fechada";
//        else
//            return "Aberta";
//    }

    public String getBuyDate() {
        return buyDate;
    }
    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getBuyDateFormated() {
        //SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        String buyDateFormated = "";

        if (buyDate != null) {
            try {
////                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
//                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
//                Date date = format.parse(buyDate);
//
//                buyDateFormated = format.format(date);

                Date initDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss a").parse(buyDate);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                buyDateFormated = sdf.format(initDate);
            } catch (Exception e) {
                //java.text.ParseException: Unparseable date: Geting error
                System.out.println("Excep" + e);
            }

        }

        return buyDateFormated;
    }

    public Integer getQtdItens() {
        return qtdItens;
    }
    public void setQtdItens(Integer qtdItens) {
        this.qtdItens = qtdItens;
    }

    public float getTotalPaid() {
        return totalPaid;
    }
    public void setTotalPaid(float totalPaid) {
        this.totalPaid = totalPaid;
    }
    public String getTotalPaidFormated() {
        Locale ptBr = new Locale("pt", "BR");
        NumberFormat f = NumberFormat.getNumberInstance(ptBr);
        f.setMinimumFractionDigits(2);
        return f.format(totalPaid);
    }


    public float getTotalAmount () {
        return totalAmount ;
    }
    public void setTotalAmount (float totalAmount ) {
        this.totalAmount  = totalAmount ;
    }
    public String getTotalAmountFormated() {
        Locale ptBr = new Locale("pt", "BR");
        NumberFormat f = NumberFormat.getNumberInstance(ptBr);
        f.setMinimumFractionDigits(2);
        return f.format(totalAmount );
    }

    public float getTotalDiscount() {
        return totalDiscount;
    }
    public void setTotalDiscount(Float totalDiscount) {
        this.totalDiscount= totalDiscount ;
    }
    public String getTotalDiscountFormated() {
        Locale ptBr = new Locale("pt", "BR");
        NumberFormat f = NumberFormat.getNumberInstance(ptBr);
        f.setMinimumFractionDigits(2);
        return f.format(getTotalDiscount());
    }


}

