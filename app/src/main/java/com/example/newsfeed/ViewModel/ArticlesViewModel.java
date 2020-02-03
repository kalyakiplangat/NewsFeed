package com.example.newsfeed.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsfeed.activity.MainActivity;
import com.example.newsfeed.adapter.ArticleRecyclerAdapter;
import com.example.newsfeed.model.Articles;
import com.example.newsfeed.model.ResponseModel;
import com.example.newsfeed.rest.ArticleInterface;
import com.example.newsfeed.rest.RestApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesViewModel extends ViewModel {
    private MutableLiveData<List<Articles>> articles;

    public LiveData<List<Articles>> getArticles(){
        if (articles == null){
            articles = new MutableLiveData<List<Articles>>();
            loadData();
        }
        return articles;

    }

    private void loadData() {
        ArticleInterface mInterface = RestApiClient.getClient().create(ArticleInterface.class);
        String country = "techcrunch";
        Call<ResponseModel> call;
        call = mInterface.getLatestNews(country);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                if (response.isSuccessful() && response.body().getArticles() != null){
                    articles.setValue(response.body().getArticles());
                    Log.d("data onResponse","s"+response.body().getArticles());

                }else {
                    Log.d("Error onResponse","could not load the data");
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });

    }

}


























