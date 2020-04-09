package br.com.bmgsistemas.supermarket.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.bmgsistemas.supermarket.entity.AppEntity;
import br.com.bmgsistemas.supermarket.entity.EcommerceCategoryEntity;



@Dao
public interface AppDao {
    @Query("SELECT * FROM AppEntity")
    AppEntity get();

    @Query("DELETE FROM AppEntity")
    void deleteAll();

    @Insert
    void insert(AppEntity app);

    @Delete
    void delete(AppEntity app);
}
