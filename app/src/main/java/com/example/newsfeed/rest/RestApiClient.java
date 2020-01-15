package com.example.newsfeed.rest;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {
    private static final String Base_URL = "https://newsapi.org/v2/";
    private static Retrofit retrofit = null;



    public static Retrofit getClient(){

     HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
     httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(httpLoggingInterceptor);
        httpClient.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("X-Api-Key", "67c24956036d4d539197df1573243782")
                        .header("Accept", "application/json")
                        .build();

                return chain.proceed(request);
            }
        });


        OkHttpClient client = httpClient.build();
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

}
