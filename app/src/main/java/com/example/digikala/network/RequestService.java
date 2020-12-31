package com.example.digikala.network;

import com.example.digikala.model.customer.CreateCustomer;
import com.example.digikala.model.product.CategoriesItem;
import com.example.digikala.model.product.ProductsItem;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RequestService {

    @GET("products/")
    Call<List<ProductsItem>> getProducts(@QueryMap Map<String, String> map);

    @GET("products/{id}/")
    Call<ProductsItem> getSingleProduct(@Path("id") int productId, @QueryMap Map<String, String> map);

    @GET("products/categories/")
    Call<List<CategoriesItem>> getCategories(@QueryMap Map<String, String> map);


    @POST("customers")
    Call<CreateCustomer> createCustomer(@Body CreateCustomer customersItem,
                                        @QueryMap Map<String, String> map);


//    @FormUrlEncoded
//    @POST("products/reviews/")
//    Call<CustomerPost> sendPost(@QueryMap Map<String, String> map,
//                                @Field("product_id") int productId,
//                                @Field("review") String review,
//                                @Field("reviewer") String reviewer,
//                                @Field("reviewer_email") String reviewerEmail,
//                                @Field("rating") int rating);
//
//
//    @DELETE("products/reviews/{id}/")
//    Call<CustomerPost> deletePost(@Path("id") int id);


}
