package br.com.bmgsistemas.supermarket.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity
public class ProductEntity implements Serializable {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "BarCode")
    public String BarCode;

    @ColumnInfo(name = "Name")
    public String Name;

    @ColumnInfo(name = "ProductBrandID")
    public String ProductBrandID;

    @ColumnInfo(name = "ProductCategoryID;")
    public String ProductCategoryID;

    @ColumnInfo(name = "ManufacturerID;")
    public String ManufacturerID;

    @ColumnInfo(name = "NCM;")
    public String NCM;

//    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "Image")
//    public byte[] image;

}
