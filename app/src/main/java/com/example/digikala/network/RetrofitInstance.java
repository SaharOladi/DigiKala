package com.example.digikala.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.digikala.network.NetworkParam.BASE_URL;

public class RetrofitInstance {


    public static Retrofit getInstance() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }


}

