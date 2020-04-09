package br.com.bmgsistemas.supermarket.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.bmgsistemas.supermarket.entity.ShoppingEntity;
import br.com.bmgsistemas.supermarket.models.Shopping;


@Dao
public interface ShoppingDao {

    @Query("SELECT sh.ID as id, sh.StoreID as storeID, sh.BuyDate as buyDate, sh.Name as name, sh.TotalAmount totalAmount, sh.Qtd as qtdItens, sh.Status as status, sh.TotalDiscount as totalDiscount, sh.TotalPaid as totalPaid, st.Name as storeName  FROM ShoppingEntity sh INNER JOIN StoreEntity st ON sh.StoreID = st.ID ORDER BY sh.BuyDate DESC")
    List<Shopping> getAllByBuyDateDesc();

    @Query("SELECT sh.ID as id, sh.StoreID as storeID, sh.BuyDate as buyDate, sh.Name as name, sh.TotalAmount totalAmount, sh.Qtd as qtdItens, sh.Status as status, sh.TotalDiscount as totalDiscount, sh.TotalPaid as totalPaid, st.Name as storeName FROM ShoppingEntity sh INNER JOIN StoreEntity st ON sh.StoreID = st.ID ORDER BY sh.BuyDate ")
    List<Shopping> getAllByBuyDateAsc();

    @Query("SELECT sh.ID as id, sh.StoreID as storeID, sh.BuyDate as buyDate, sh.Name as name, sh.TotalAmount totalAmount, sh.Qtd as qtdItens, sh.Status as status, sh.TotalDiscount as totalDiscount, sh.TotalPaid as totalPaid, st.Name as storeName  FROM ShoppingEntity sh INNER JOIN StoreEntity st ON sh.StoreID = st.ID WHERE sh.buyDate LIKE :buyDate ")
    List<Shopping> findByBuyDate(String buyDate);

    @Query("SELECT sh.ID as id, sh.StoreID as storeID, sh.BuyDate as buyDate, sh.Name as name, sh.TotalAmount totalAmount, sh.Qtd as qtdItens, sh.Status as status, sh.TotalDiscount as totalDiscount, sh.TotalPaid as totalPaid, st.Name as storeName  FROM ShoppingEntity sh INNER JOIN StoreEntity st ON sh.StoreID = st.ID WHERE sh.id = :id ")
    Shopping findByID(String id);

    @Query("SELECT *  FROM ShoppingEntity WHERE id = :id ")
    ShoppingEntity findEntityByID(String id);

    @Query("DELETE FROM ShoppingEntity")
    void deleteAll();

    @Insert
    void insert(ShoppingEntity shopping);

    @Update
    int  update(ShoppingEntity shopping);

    @Delete
    void delete(ShoppingEntity shopping);
}
