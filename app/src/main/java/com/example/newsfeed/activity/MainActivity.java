package com.example.newsfeed.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsfeed.R;
import com.example.newsfeed.adapter.ArticleRecyclerAdapter;
import com.example.newsfeed.databinding.ContentMainBinding;
import com.example.newsfeed.model.Articles;
import com.example.newsfeed.model.ResponseModel;
import com.example.newsfeed.rest.ArticleInterface;
import com.example.newsfeed.rest.RestApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "ac6f8702d7ca442b99ae381ded10be6a";
    private RecyclerView mRecyclerView;
    private List<Articles> mArticles = new ArrayList<>();
    private ArticleRecyclerAdapter mAdapter;

    private ContentMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.content_main);
//        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        mRecyclerView = (RecyclerView) findViewById(R.id.list_articles);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainBinding.listArticles.setLayoutManager(layoutManager);

        mAdapter = new ArticleRecyclerAdapter(this, mArticles);

        loadData();
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

                    mArticles = response.body().getArticles();
                    mAdapter = new ArticleRecyclerAdapter(MainActivity.this, mArticles);
                    mainBinding.listArticles.setAdapter(mAdapter);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
