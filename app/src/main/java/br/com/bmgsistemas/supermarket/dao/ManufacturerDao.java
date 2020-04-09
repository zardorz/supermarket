package br.com.bmgsistemas.supermarket.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.bmgsistemas.supermarket.entity.ManufacturerEntity;

@Dao
public interface ManufacturerDao {


    @Query("SELECT * FROM ManufacturerEntity")
    List<ManufacturerEntity> getAll();

    @Query("SELECT * FROM ManufacturerEntity WHERE name LIKE :name ")
    List<ManufacturerEntity> findByName(String name);

    @Query("SELECT * FROM ManufacturerEntity WHERE id LIKE :id ")
    List<ManufacturerEntity> findByID(String id);

    @Query("DELETE FROM ManufacturerEntity")
    void deleteAll();

    @Insert
    void insert(ManufacturerEntity manufacturer);

    @Insert
    void insertAll(ManufacturerEntity... manufacturers);

    @Delete
    void delete(ManufacturerEntity manufacturer);
}
