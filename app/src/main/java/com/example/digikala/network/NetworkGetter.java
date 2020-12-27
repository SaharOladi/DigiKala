package com.example.digikala.network;

import android.util.Log;

import com.example.digikala.model.CategoriesItem;
import com.example.digikala.model.ProductsItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.digikala.network.NetworkParam.CONSUMER_KEY;
import static com.example.digikala.network.NetworkParam.CONSUMER_SECRET;

public class NetworkGetter {

    private final String TAG = "NetworkGetter";

    private List<ProductsItem> mProducts;
    private RequestService mRequestService;
    private NetworkState mNetworkState;

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

    public NetworkGetter() {
        mRequestService = RetrofitInstance.getInstance().create(RequestService.class);
    }

    public void fetchAllProductItemsAsync(Callbacks callBacks) {

        mRequestService.getProducts(BASE).enqueue(new Callback<List<ProductsItem>>() {
            @Override
            public void onResponse(Call<List<ProductsItem>> call, Response<List<ProductsItem>> response) {
                List<ProductsItem> items = response.body();
                callBacks.onItemResponse(items);
            }


            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
                mNetworkState.onFailureCalled();

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
                callBacks.onItemResponse(recentItems);
            }

            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
                mNetworkState.onFailureCalled();
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
                mNetworkState.onFailureCalled();
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
                callBacks.onItemResponse(ratedItems);
            }

            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
                mNetworkState.onFailureCalled();

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
                callBacks.onItemResponse(categoriesItems);
            }

            @Override
            public void onFailure(Call<List<CategoriesItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
                mNetworkState.onFailureCalled();

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
                callBacks.onItemResponse(categoryItems);
            }

            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
                mNetworkState.onFailureCalled();

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
                callBacks.onItemResponse(productsItem);
            }

            @Override
            public void onFailure(Call<ProductsItem> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
                mNetworkState.onFailureCalled();

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
                callBacks.onItemResponse(searchItems);
            }

            @Override
            public void onFailure(Call<List<ProductsItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
                mNetworkState.onFailureCalled();

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

    public interface NetworkState {
        void onFailureCalled();
    }

}
