package com.example.newsfeed.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleService;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.newsfeed.R;
import com.example.newsfeed.adapter.ArticleRecyclerAdapter;
import com.example.newsfeed.model.Articles;
import com.example.newsfeed.model.ResponseModel;
import com.example.newsfeed.rest.ArticleInterface;
import com.example.newsfeed.rest.RestApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<Articles> articlesList;


    public TechFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tech, container, false);
        recyclerView = view.findViewById(R.id.techRecyclerView);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        loadArticles();
        return view;
    }

    private void loadArticles() {
        ArticleInterface mInterface = RestApiClient.getClient().create(ArticleInterface.class);
        String source = "techcrunch";
        Call<ResponseModel> call;
        call = mInterface.getLatestNews(source);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                if (response.isSuccessful() && response.body().getArticles() != null){
                    articlesList = response.body().getArticles();
                    ArticleRecyclerAdapter articleRecyclerAdapter = new ArticleRecyclerAdapter(getActivity(), articlesList);
                    recyclerView.setAdapter(articleRecyclerAdapter);
                    Log.d("data onResponse","s"+response.body().getArticles());

                }else {
                    Log.d("Error onResponse","could not load the data");
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Fail to load tech related articles", Toast.LENGTH_SHORT).show();
                Log.d("MainActivity", "error loading from API");
            }
        });
    }

}
