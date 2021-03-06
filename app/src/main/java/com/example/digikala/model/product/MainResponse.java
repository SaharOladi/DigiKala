package com.example.digikala.model.product;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MainResponse {

    @SerializedName("products")
    private List<ProductsItem> products;

    public List<ProductsItem> getProducts() {
        return products;
    }
}

