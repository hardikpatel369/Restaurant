package com.nspl.restaurant.Global;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;

    private static final String BASE_URL = "http://136.233.136.43:81/api/";
//    private static final String BASE_URL = "http://192.168.1.188:81/api/";
//    private static final String BASE_URL = "http://192.168.1.55:90/api/";


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
