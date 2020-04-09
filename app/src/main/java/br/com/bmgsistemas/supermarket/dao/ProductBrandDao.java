package br.com.bmgsistemas.supermarket.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.bmgsistemas.supermarket.entity.ProductBrandEntity;
import br.com.bmgsistemas.supermarket.entity.ProductCategoryEntity;

@Dao
public interface ProductBrandDao {

    @Query("SELECT * FROM ProductBrandEntity")
    List<ProductBrandEntity> getAll();

    @Query("SELECT * FROM ProductBrandEntity WHERE name LIKE :name ")
    List<ProductBrandEntity> findByName(String name);

    @Query("SELECT * FROM ProductBrandEntity WHERE id LIKE :id ")
    List<ProductBrandEntity> findByID(String id);

    @Query("DELETE FROM ProductBrandEntity")
    void deleteAll();

    @Insert
    void insert(ProductBrandEntity productBrand);

    @Insert
    void insertAll(ProductBrandEntity... productBrands);

    @Delete
    void delete(ProductBrandEntity productBrand);

}
