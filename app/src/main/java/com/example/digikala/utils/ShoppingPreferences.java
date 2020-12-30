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

    public static void setPrefIntArray(Context context, int[] value) {
        SharedPreferences.Editor prefEditor = getSharedPreferences(context).edit();
        String s;
        try {
            JSONArray jsonArr = new JSONArray();
            for (int i : value)
                jsonArr.put(i);

            JSONObject json = new JSONObject();
            json.put(SHOP_PREF_KEY, jsonArr);

            s = json.toString();
        } catch (JSONException excp) {
            s = "";
        }

        prefEditor.putString(SHOP_PREF_KEY, s);
        prefEditor.commit();
    }

    public static int[] getPrefIntArray(Context context) {
        SharedPreferences pref = getSharedPreferences(context);
        String s = pref.getString(SHOP_PREF_KEY, "");
        try {
            JSONObject json = new JSONObject(new JSONTokener(s));
            JSONArray jsonArr = json.getJSONArray(SHOP_PREF_KEY);

            int[] result = new int[jsonArr.length()];
            for (int i = 0; i < jsonArr.length(); i++)
                result[i] = jsonArr.getInt(i);
            return result;
        } catch (JSONException excp) {
            return null;
        }
    }
}
