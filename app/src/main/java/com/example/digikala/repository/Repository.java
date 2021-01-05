package com.example.digikala.repository;

import android.util.Log;


import com.example.digikala.model.customer.Customer;
import com.example.digikala.model.product.CategoriesItem;
import com.example.digikala.model.product.ProductsItem;
import com.example.digikala.network.RequestService;
import com.example.digikala.network.RetrofitInstance;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.digikala.network.NetworkParam.CONSUMER_KEY;
import static com.example.digikala.network.NetworkParam.CONSUMER_SECRET;

public class Repository {

    private final String TAG = "Repository";

    private List<ProductsItem> mProducts;
    private RequestService mRequestService;

    public static final Map<String, String> BASE = new HashMap<String, String>() {{
        put("consumer_key", CONSUMER_KEY);
        put("consumer_secret", CONSUMER_SECRET);

    }};

    public List<ProductsItem> getProducts() {
        return mProducts;
    }

    public void setProducts(List<ProductsItem> products) {
        mProducts = products;
    }

    public Repository() {
        mRequestService = RetrofitInstance.getInstance().create(RequestService.class);
    }

    public void fetchAllProductItemsAsync(Callbacks callBacks) {

        mRequestService.getProducts(BASE).enqueue(new Callback<List<ProductsItem>>() {
            @Override
            public void onResponse(Call<List<ProductsItem>> call, Response<List<ProductsItem>> response) {
                List<ProductsItem> items = response.body();
                //update adapter of recyclerview
                callBacks.onItemResponse(items);
            }


            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);

            }
        });
    }

    public void fetchRecentProducts(int page, Callbacks callBacks) {
        HashMap<String, String> insideMap = new HashMap<>();

        insideMap.putAll(BASE);
        insideMap.put("page", String.valueOf(page));
        insideMap.put("orderby", "date");

        mRequestService.getProducts(insideMap).enqueue(new Callback<List<ProductsItem>>() {
            @Override
            public void onResponse(Call<List<ProductsItem>> call, Response<List<ProductsItem>> response) {

                List<ProductsItem> recentItems = response.body();
                //update adapter of recyclerview
                callBacks.onItemResponse(recentItems);
            }

            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }


    public void fetchMostVisitedProducts(int page, Callbacks callBacks) {
        HashMap<String, String> insideMap = new HashMap<>();

        insideMap.putAll(BASE);
        insideMap.put("page", String.valueOf(page));
        insideMap.put("orderby", "rating");

        mRequestService.getProducts(insideMap).enqueue(new Callback<List<ProductsItem>>() {
            @Override
            public void onResponse(Call<List<ProductsItem>> call, Response<List<ProductsItem>> response) {

                List<ProductsItem> mostVisitedItems = response.body();
                //update adapter of recyclerview
                callBacks.onItemResponse(mostVisitedItems);
            }

            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void fetchRatedProducts(int page, Callbacks callBacks) {
        HashMap<String, String> insideMap = new HashMap<>();

        insideMap.putAll(BASE);
        insideMap.put("page", String.valueOf(page));
        insideMap.put("orderby", "popularity");

        mRequestService.getProducts(insideMap).enqueue(new Callback<List<ProductsItem>>() {
            @Override
            public void onResponse(Call<List<ProductsItem>> call, Response<List<ProductsItem>> response) {

                List<ProductsItem> ratedItems = response.body();
                //update adapter of recyclerview
                callBacks.onItemResponse(ratedItems);
            }

            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void fetchCategory(int page, CategoryCallbacks callBacks) {
        HashMap<String, String> insideMap = new HashMap<>();

        insideMap.putAll(BASE);
        insideMap.put("page", String.valueOf(page));
        insideMap.put("per_page", String.valueOf(10));

        mRequestService.getCategories(insideMap).enqueue(new Callback<List<CategoriesItem>>() {
            @Override
            public void onResponse(Call<List<CategoriesItem>> call, Response<List<CategoriesItem>> response) {
                List<CategoriesItem> categoriesItems = response.body();
                //update adapter of recyclerview
                callBacks.onItemResponse(categoriesItems);
            }

            @Override
            public void onFailure(Call<List<CategoriesItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);

            }
        });
    }

    public void fetchCategoryProduct(int page, int id, Callbacks callBacks) {
        HashMap<String, String> localMap = new HashMap<>();

        localMap.putAll(BASE);
        localMap.put("page", String.valueOf(page));
        localMap.put("category", String.valueOf(id));

        mRequestService.getProducts(localMap).enqueue(new Callback<List<ProductsItem>>() {
            @Override
            public void onResponse(Call<List<ProductsItem>> call, Response<List<ProductsItem>> response) {

                List<ProductsItem> categoryItems = response.body();
                //update adapter of recyclerview
                callBacks.onItemResponse(categoryItems);
            }

            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void fetchCategoryProductByOrder(int page, int id, String orderBy, Callbacks callBacks) {
        HashMap<String, String> localMap = new HashMap<>();

        localMap.putAll(BASE);
        localMap.put("page", String.valueOf(page));
        localMap.put("category", String.valueOf(id));
        localMap.put("orderby", orderBy);

        mRequestService.getProducts(localMap).enqueue(new Callback<List<ProductsItem>>() {
            @Override
            public void onResponse(Call<List<ProductsItem>> call, Response<List<ProductsItem>> response) {
                List<ProductsItem> categoryItems = response.body();
                //update adapter of recyclerview
                callBacks.onItemResponse(categoryItems);
            }

            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }


    public void fetchSingleProduct(int id, SingleCallbacks callBacks) {
        HashMap<String, String> insideMap = new HashMap<>();

        insideMap.putAll(BASE);

        mRequestService.getSingleProduct(id, insideMap).enqueue(new Callback<ProductsItem>() {
            @Override
            public void onResponse(Call<ProductsItem> call, Response<ProductsItem> response) {

                ProductsItem productsItem = response.body();
                //update adapter of recyclerview
                callBacks.onItemResponse(productsItem);
            }

            @Override
            public void onFailure(Call<ProductsItem> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void sendCustomer(String email, CustomerCallbacks customerCallbacks) {

        mRequestService.createCustomer(email).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                Log.d(TAG, "onResponse: " + response.body());
                Log.d(TAG, "onResponse: " + response.code());

            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

            }
        });

    }


    public void fetchSearchProducts(String query, Callbacks callBacks) {

        HashMap<String, String> insideMap = new HashMap<>();

        insideMap.putAll(BASE);
        insideMap.put("search", query);

        mRequestService.getProducts(insideMap).enqueue(new Callback<List<ProductsItem>>() {
            @Override
            public void onResponse(Call<List<ProductsItem>> call, Response<List<ProductsItem>> response) {
                List<ProductsItem> searchItems = response.body();
                //update adapter of recyclerview
                callBacks.onItemResponse(searchItems);
            }

            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }


    public interface Callbacks {
        void onItemResponse(List<ProductsItem> items);
    }

    public interface CategoryCallbacks {
        void onItemResponse(List<CategoriesItem> items);
    }

    public interface SingleCallbacks {
        void onItemResponse(ProductsItem item);
    }

    public interface CustomerCallbacks {
        void onItemResponse(Customer createCustomer);
    }


}
