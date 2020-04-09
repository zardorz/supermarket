package br.com.bmgsistemas.supermarket.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.bmgsistemas.supermarket.entity.SupllierEntity;

@Dao
public interface SupllierDao {

    @Query("SELECT * FROM SupllierEntity")
    List<SupllierEntity> getAll();

    @Query("SELECT * FROM SupllierEntity WHERE name LIKE :name ")
    List<SupllierEntity> findByName(String name);

    @Query("SELECT * FROM SupllierEntity WHERE id LIKE :id ")
    List<SupllierEntity> findByID(String id);

    @Query("DELETE FROM SupllierEntity")
    void deleteAll();

    @Insert
    void insert(SupllierEntity supllier);

    @Insert
    void insertAll(SupllierEntity... suplliers);

    @Delete
    void delete(SupllierEntity supllier);
}
