package br.com.bmgsistemas.supermarket.core;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


import br.com.bmgsistemas.supermarket.dao.AppDao;
import br.com.bmgsistemas.supermarket.dao.CartDao;
import br.com.bmgsistemas.supermarket.dao.EcommerceCategoryDao;
import br.com.bmgsistemas.supermarket.dao.ManufacturerDao;
import br.com.bmgsistemas.supermarket.dao.ProductBrandDao;
import br.com.bmgsistemas.supermarket.dao.ProductCategoryDao;
import br.com.bmgsistemas.supermarket.dao.ProductDao;
import br.com.bmgsistemas.supermarket.dao.ShoppingDao;
import br.com.bmgsistemas.supermarket.dao.StoreDao;

import br.com.bmgsistemas.supermarket.entity.AppEntity;
import br.com.bmgsistemas.supermarket.entity.EcommerceCategoryEntity;
import br.com.bmgsistemas.supermarket.entity.ManufacturerEntity;
import br.com.bmgsistemas.supermarket.entity.ProductBrandEntity;
import br.com.bmgsistemas.supermarket.entity.ProductCategoryEntity;
import br.com.bmgsistemas.supermarket.entity.ProductEntity;
import br.com.bmgsistemas.supermarket.entity.ShoppingEntity;
import br.com.bmgsistemas.supermarket.entity.CartEntity;
import br.com.bmgsistemas.supermarket.entity.StoreEntity;
import br.com.bmgsistemas.supermarket.models.Shopping;


@Database(entities = {
                ProductEntity.class,
                StoreEntity.class,
                ProductBrandEntity.class,
                ProductCategoryEntity.class,
                ManufacturerEntity.class,
                EcommerceCategoryEntity.class,
                ShoppingEntity.class,
                CartEntity.class,
                AppEntity.class},
          version = 12,
          exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    public abstract StoreDao storeDao();
    public abstract ProductBrandDao productBrandDao();
    public abstract ProductCategoryDao productCategoryDao();
    //public abstract SupllierDao supllierDao();
    public abstract ManufacturerDao manufacturerDao();
    public abstract EcommerceCategoryDao ecommerceCategoryDao();
    public abstract ShoppingDao  shoppingDao();
    public abstract CartDao cartDao();
    public abstract AppDao appDao();

}