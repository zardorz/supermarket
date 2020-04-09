package br.com.bmgsistemas.supermarket.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.bmgsistemas.supermarket.entity.ProductEntity;
import br.com.bmgsistemas.supermarket.models.Product;

@Dao
public interface  ProductDao {

    @Query("SELECT * FROM ProductEntity")
    List<ProductEntity> getAll();

    @Query("SELECT * FROM ProductEntity WHERE name LIKE :name ")
    List<ProductEntity> findByName(String name);

    @Query("SELECT * FROM ProductEntity WHERE barCode LIKE :barCode ")
    ProductEntity findByBarCode(String barCode);

    @Query("DELETE FROM ProductEntity")
    void deleteAll();

    @Insert
    void insert(ProductEntity product);

    @Insert
    void insertAll(ProductEntity... products);

    @Delete
    void delete(ProductEntity product);
}
