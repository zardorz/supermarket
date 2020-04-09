package br.com.bmgsistemas.supermarket.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.bmgsistemas.supermarket.entity.CartEntity;
import br.com.bmgsistemas.supermarket.models.CartProduct;

@Dao
public interface CartDao {
    @Query("SELECT * FROM CartEntity")
    List<CartEntity> getAll();

    @Query("SELECT * FROM CartEntity WHERE ID = :id")
    CartEntity findByID(String id);

    @Query("SELECT " +
            " c.ID as CartID" +
            " ,c.ShoppingID" +
            " ,c.ProductBarCode" +
            " ,p.Name as ProductName" +
            " ,c.Amount as ProductAmount" +
            " ,c.Qtd as ProductQtd" +
            " ,c.TotalAmount as ProductTotalAmount" +
            " ,p.`ManufacturerID;` as ProductManufacturerID"  +
            " ,p.`NCM;`  as ProductNCM" +
            " ,p.`ProductCategoryID;` as ProductCategoryID" +
            " ,p.ProductBrandID as ProductBrandID" +
            " FROM CartEntity c inner join ProductEntity p on c.ProductBarCode == p.BarCode WHERE c.ProductBarCode LIKE :barCode")
    List<CartProduct> findCartProductByBarCode(String barCode);

    @Query("SELECT " +
            " c.ID as CartID" +
            " ,c.ShoppingID" +
            " ,c.ProductBarCode" +
            " ,p.Name as ProductName" +
            " ,c.Amount as ProductAmount" +
            " ,c.Qtd as ProductQtd" +
            " ,c.TotalAmount as ProductTotalAmount" +
            " ,p.`ManufacturerID;` as ProductManufacturerID"  +
            " ,p.`NCM;`  as ProductNCM" +
            " ,p.`ProductCategoryID;` as ProductCategoryID" +
            " ,p.ProductBrandID as ProductBrandID" +
            " FROM CartEntity c inner join ProductEntity p on c.ProductBarCode == p.BarCode WHERE  c.ShoppingID = :shoppingID ORDER BY p.Name")
    List<CartProduct> findCartProductByShoppingID(String shoppingID);

    @Query("SELECT " +
            " c.ID as CartID" +
            " ,c.ShoppingID" +
            " ,c.ProductBarCode" +
            " ,p.Name as ProductName" +
            " ,c.Amount as ProductAmount" +
            " ,c.Qtd as ProductQtd" +
            " ,c.TotalAmount as ProductTotalAmount" +
            " ,p.`ManufacturerID;` as ProductManufacturerID"  +
            " ,p.`NCM;`  as ProductNCM" +
            " ,p.`ProductCategoryID;` as ProductCategoryID" +
            " ,p.ProductBrandID as ProductBrandID" +
            " FROM CartEntity c inner join ProductEntity p on c.ProductBarCode == p.BarCode  WHERE c.ID = :cartID")
    List<CartProduct> findCartProductByID(String cartID);



    @Query("DELETE FROM CartEntity")
    void deleteAll();

    @Query("DELETE FROM CartEntity WHERE ShoppingID LIKE :shoppingID")
    void deleteCardByShoppingID(String shoppingID);

    @Insert
    void insert(CartEntity cart);

    @Update
    void update(CartEntity cart);

    @Insert
    void insertAll(CartEntity... carties);

    @Delete
    void delete(CartEntity cart);
}
