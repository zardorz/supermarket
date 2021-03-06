package br.com.bmgsistemas.supermarket.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class ProductCategoryEntity {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "ID")
    public String ID;

    @ColumnInfo(name = "Name")
    public String Name;

    @Override
    public String toString() {
        return Name;
    }
}
