package br.com.bmgsistemas.supermarket.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


import br.com.bmgsistemas.supermarket.entity.ProductCategoryEntity;

@Dao
public interface ProductCategoryDao {
    @Query("SELECT * FROM ProductCategoryEntity")
    List<ProductCategoryEntity> getAll();

    @Query("SELECT * FROM ProductCategoryEntity WHERE name LIKE :name ")
    List<ProductCategoryEntity> findByName(String name);

    @Query("SELECT * FROM ProductCategoryEntity WHERE id LIKE :id ")
    List<ProductCategoryEntity> findByID(String id);

    @Query("DELETE FROM ProductCategoryEntity")
    void deleteAll();

    @Insert
    void insert(ProductCategoryEntity productCategory);

    @Insert
    void insertAll(ProductCategoryEntity... productCategories);

    @Delete
    void delete(ProductCategoryEntity productCategory);
}
