package com.example.newsfeed.rest;

import com.example.newsfeed.model.ResponseModel;
import com.example.newsfeed.sourcemodel.BaseSource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleInterface {
    @GET("top-headlines")
    Call<ResponseModel> getLatestNews(@Query("sources") String source);

    @GET("everything")
    Call<com.example.newsfeed.model.basemodel.BaseSource> getAllArticles(@Query("domains") String domain);

    @GET("sources")
    Call<BaseSource> getSourceTechCategory(@Query("category") String category);

}
