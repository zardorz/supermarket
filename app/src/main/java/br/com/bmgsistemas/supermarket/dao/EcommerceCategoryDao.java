package br.com.bmgsistemas.supermarket.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.bmgsistemas.supermarket.entity.EcommerceCategoryEntity;

@Dao
public interface EcommerceCategoryDao {
    @Query("SELECT * FROM EcommerceCategoryEntity")
    List<EcommerceCategoryEntity> getAll();

    @Query("SELECT * FROM EcommerceCategoryEntity WHERE name LIKE :name")
    List<EcommerceCategoryEntity> findByName(String name);

    @Query("SELECT * FROM EcommerceCategoryEntity WHERE id LIKE :id")
    List<EcommerceCategoryEntity> findByID(String id);

    @Query("DELETE FROM EcommerceCategoryEntity")
    void deleteAll();

    @Insert
    void insert(EcommerceCategoryEntity ecommerceCategory);

    @Insert
    void insertAll(EcommerceCategoryEntity... ecommerceCategories);

    @Delete
    void delete(EcommerceCategoryEntity ecommerceCategory);
}
