package com.example.newsfeed.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsfeed.R;
import com.example.newsfeed.adapter.CategoryAdapter;
import com.example.newsfeed.sourcemodel.BaseSource;
import com.example.newsfeed.sourcemodel.Source;
import com.example.newsfeed.rest.ArticleInterface;
import com.example.newsfeed.rest.RestApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SourcesFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<Source> categories;


    public SourcesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sources, container, false);
        recyclerView = view.findViewById(R.id.sourceRecyclerView);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        loadSources();
        return view;
    }

    private void loadSources() {
        ArticleInterface articleInterface = RestApiClient.getClient().create(ArticleInterface.class);
        String category = "technology";
        Call<BaseSource> call;
        call = articleInterface.getSourceTechCategory(category);
        call.enqueue(new Callback<BaseSource>() {
            @Override
            public void onResponse(Call<BaseSource> call, Response<BaseSource> response) {
                categories = response.body().getSources();
                CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), categories);
                recyclerView.setAdapter(categoryAdapter);
            }

            @Override
            public void onFailure(Call<BaseSource> call, Throwable t) {

            }
        });
    }

}
