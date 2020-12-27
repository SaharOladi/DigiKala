package com.example.digikala.repository;

import android.content.Context;

import com.example.digikala.model.CategoriesItem;
import com.example.digikala.model.ProductsItem;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private Context mContext;
    private static Repository sInstance;

    public Repository(Context context) {
        mContext = context;
    }

    public static Repository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new Repository(context);
        return sInstance;
    }


    private List<ProductsItem> mAllProductsItems = new ArrayList<>();
    private List<ProductsItem> mMostVisitedProductsItems = new ArrayList<>();
    private List<ProductsItem> mRecentProductsItems = new ArrayList<>();
    private List<ProductsItem> mRatedProductsItems = new ArrayList<>();
    private List<ProductsItem> mCategoryProductsItems = new ArrayList<>();
    private List<CategoriesItem> mCategoriesItems = new ArrayList<>();

    private ProductsItem mSingleProductsItem;


    public List<ProductsItem> getAllProductsItems() {
        return mAllProductsItems;
    }

    public void setAllProductsItems(List<ProductsItem> allProductsItems) {
        mAllProductsItems = allProductsItems;
    }

    public List<ProductsItem> getMostVisitedProductsItems() {
        return mMostVisitedProductsItems;
    }

    public void setMostVisitedProductsItems(List<ProductsItem> mostVisitedProductsItems) {
        mMostVisitedProductsItems = mostVisitedProductsItems;
    }

    public List<ProductsItem> getRecentProductsItems() {
        return mRecentProductsItems;
    }

    public void setRecentProductsItems(List<ProductsItem> recentProductsItems) {
        mRecentProductsItems = recentProductsItems;
    }

    public List<ProductsItem> getRatedProductsItems() {
        return mRatedProductsItems;
    }

    public void setRatedProductsItems(List<ProductsItem> ratedProductsItems) {
        mRatedProductsItems = ratedProductsItems;
    }

    public List<ProductsItem> getCategoryProductsItems() {
        return mCategoryProductsItems;
    }

    public void setCategoryProductsItems(List<ProductsItem> categoryProductsItems) {
        mCategoryProductsItems = categoryProductsItems;
    }

    public List<CategoriesItem> getCategoriesItems() {
        return mCategoriesItems;
    }

    public void setCategoriesItems(List<CategoriesItem> categoriesItems) {
        mCategoriesItems = categoriesItems;
    }

    public ProductsItem getSingleProductsItem() {
        return mSingleProductsItem;
    }

    public void setSingleProductsItem(ProductsItem singleProductsItem) {
        mSingleProductsItem = singleProductsItem;
    }
}
