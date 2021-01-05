package com.example.digikala.database.customer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.digikala.model.product.ProductsItem;

import java.util.List;


@Dao
public interface ProductDao {

    @Insert
    void insertProduct(ProductsItem productsItem);

    @Delete
    void deleteProduct(ProductsItem productsItem);

    @Query("SELECT * FROM productTable WHERE id=:inputId")
    ProductsItem getProductItem(int inputId);

    @Query("SELECT * FROM productTable")
    List<ProductsItem> getProducts();


}
