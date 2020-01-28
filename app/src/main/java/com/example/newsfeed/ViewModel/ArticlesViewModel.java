package com.example.newsfeed.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsfeed.model.Articles;

import java.util.List;

public class ArticlesViewModel extends ViewModel {
    private MutableLiveData<List<Articles>> articles;

    public LiveData<List<Articles>> getArticles(){
        if (articles == null){
            articles = new MutableLiveData<List<Articles>>();

        }
        return articles;
    }
 }
