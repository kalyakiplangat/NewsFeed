package com.example.newsfeed.rest;

import com.example.newsfeed.model.Articles;
import com.example.newsfeed.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleInterface {
    @GET("top-headlines")
    Call<ResponseModel> getLatestNews(@Query("sources") String source);

}
