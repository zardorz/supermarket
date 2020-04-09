package br.com.bmgsistemas.supermarket.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class CartEntity {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "ID")
    public String ID ;

    @ColumnInfo(name = "ShoppingID")
    public String ShoppingID ;

    @ColumnInfo(name = "ProductBarCode")
    public String ProductBarCode ;

    @ColumnInfo(name = "Qtd")
    public Integer Qtd ;

    @ColumnInfo(name = "Amount")
    public double Amount ;

    @ColumnInfo(name = "TotalAmount")
    public double TotalAmount ;
}
