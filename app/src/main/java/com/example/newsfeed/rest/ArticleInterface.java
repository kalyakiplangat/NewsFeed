package com.example.newsfeed.rest;

import com.example.newsfeed.model.Articles;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleInterface {
    @GET("top_headlines")
    Call<Articles> getLatestNews(@Query("sources") String source, @Query("apiKey") String apiKey);
}
