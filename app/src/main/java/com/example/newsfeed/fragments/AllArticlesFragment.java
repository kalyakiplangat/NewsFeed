package com.example.newsfeed.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.newsfeed.R;
import com.example.newsfeed.adapter.AllArticlesAdapter;
import com.example.newsfeed.model.basemodel.AllArticles;
import com.example.newsfeed.model.basemodel.BaseSource;
import com.example.newsfeed.rest.ArticleInterface;
import com.example.newsfeed.rest.RestApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllArticlesFragment extends Fragment {

    private List<AllArticles> allArticles;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    public AllArticlesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_articles, container, false);
        recyclerView = view.findViewById(R.id.articleRecyclerView);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        loadAllArticles();
        return view;
    }

    private void loadAllArticles() {
        ArticleInterface articleInterface = RestApiClient.getClient().create(ArticleInterface.class);
        String domains = "wsj.com";
        Call<BaseSource> call;
        call = articleInterface.getAllArticles(domains);
        call.enqueue(new Callback<BaseSource>() {
            @Override
            public void onResponse(Call<BaseSource> call, Response<BaseSource> response) {
                allArticles = response.body().getArticles();
                AllArticlesAdapter allArticlesAdapter = new AllArticlesAdapter(getActivity(), allArticles);
                recyclerView.setAdapter(allArticlesAdapter);
            }

            @Override
            public void onFailure(Call<BaseSource> call, Throwable t) {
                Toast.makeText(getContext(), "Fail to load articles", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
