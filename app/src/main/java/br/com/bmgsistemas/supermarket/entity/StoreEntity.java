package br.com.bmgsistemas.supermarket.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class StoreEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "ID")
    public String ID ;

    @ColumnInfo(name = "Name")
    public String Name ;

    @ColumnInfo(name = "Description")
    public String Description ;

    @ColumnInfo(name = "Inative")
    public boolean Inative ;

    @ColumnInfo(name = "Address")
    public String Address ;

    @ColumnInfo(name = "Number")
    public String Number ;

    @ColumnInfo(name = "Complement")
    public String Complement ;

    @ColumnInfo(name = "ZipCode")
    public int ZipCode ;

    @ColumnInfo(name = "Neighborhood")
    public String Neighborhood ;

    @ColumnInfo(name = "City")
    public String City ;

    @ColumnInfo(name = "State")
    public String State ;

    @Override
    public String toString() {
        return Name;
    }


}
