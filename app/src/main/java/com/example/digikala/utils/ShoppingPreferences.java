package com.example.digikala.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ShoppingPreferences {

    private static final String SHOP_PREF_KEY = "shopKey";

    public static int getShopPrefKey(Context context) {
        return getSharedPreferences(context).getInt(SHOP_PREF_KEY, 0);
    }

    public static void setShopPrefKey(Context context, int id) {

        getSharedPreferences(context)
                .edit()
                //productItem id
                .putInt(SHOP_PREF_KEY, id)
                .apply();
    }

    public static void removeShopPrefData(Context context) {
        getSharedPreferences(context)
                .edit()
                .remove(SHOP_PREF_KEY)
                .apply();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName(), context.MODE_PRIVATE);
    }
}
