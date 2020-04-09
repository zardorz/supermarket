package br.com.bmgsistemas.supermarket.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.bmgsistemas.supermarket.entity.StoreEntity;

@Dao
public interface StoreDao {

    @Query("SELECT * FROM StoreEntity")
    List<StoreEntity> getAll();

    @Query("SELECT * FROM StoreEntity WHERE name LIKE :name ")
    List<StoreEntity> findByName(String name);

    @Query("SELECT * FROM StoreEntity WHERE id LIKE :id ")
    StoreEntity findByID(String id);

    @Query("DELETE FROM StoreEntity")
    void deleteAll();

    @Insert
    void insert(StoreEntity store);

    @Insert
    void insertAll(StoreEntity... stores);

    @Delete
    void delete(StoreEntity store);

}
