package com.example.apilearning;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiClient {
    // initializing Retrofit
    public static Retrofit RETROFIT = null;

    public static Retrofit getClient() {
        if (RETROFIT == null) {

            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            Gson gson = new GsonBuilder().create();

            //Declaring Retrofit
            RETROFIT = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.114/poetryapis/").client
                            (okHttpClient).addConverterFactory
                            (GsonConverterFactory.create(gson)).
                    build();
        }
        return RETROFIT;
    }

}
