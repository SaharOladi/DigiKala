package com.example.digikala.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.digikala.database.customer.ProductDao;
import com.example.digikala.database.customer.ProductDataBase;
import com.example.digikala.model.product.ProductsItem;

import java.util.List;


public class ShoppingRepository {

    private static ShoppingRepository sInstance;

    private ProductDao mProductDAO;
    private Context mContext;


    private ShoppingRepository(Context context) {

        mContext = context.getApplicationContext();
        ProductDataBase productDataBase = Room.databaseBuilder(mContext,
                ProductDataBase.class,
                "word.db")
                .allowMainThreadQueries()
                .build();
        mProductDAO = productDataBase.getProductDataBaseDAO();
    }


    public static ShoppingRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new ShoppingRepository(context);
        return sInstance;
    }

    public void insertProduct(ProductsItem productsItem) {
        mProductDAO.insertProduct(productsItem);
    }

    public void deleteProduct(ProductsItem productsItem) {
        mProductDAO.deleteProduct(productsItem);
    }

    public ProductsItem getProduct(int productId) {
        return mProductDAO.getProductItem(productId);
    }

    public List<ProductsItem> getProducts() {
        return mProductDAO.getProducts();
    }


}
