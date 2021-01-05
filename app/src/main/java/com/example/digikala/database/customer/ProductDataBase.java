package com.example.digikala.database.customer;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.digikala.model.product.ProductsItem;

@Database(entities = ProductsItem.class, version = 1)
@TypeConverters({Converter.class})
public abstract class ProductDataBase extends RoomDatabase {
    public abstract ProductDao getProductDataBaseDAO();
}
