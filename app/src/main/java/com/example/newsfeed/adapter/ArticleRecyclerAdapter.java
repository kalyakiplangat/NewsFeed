package com.example.newsfeed.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsfeed.R;
import com.example.newsfeed.activity.DetailActivity;
import com.example.newsfeed.databinding.ListArtclesBinding;
import com.example.newsfeed.model.Articles;

import java.util.List;


public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.ArticleViewHolder>{
    private Context mContext;
    private List<Articles> mArticles;
    private LayoutInflater mLayoutInflater;

    //class constructor
    public ArticleRecyclerAdapter(Context context, List<Articles> articles){
        this.mContext = context;
        this.mArticles = articles;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ArticleRecyclerAdapter.ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListArtclesBinding articlesBinding = DataBindingUtil.inflate(mLayoutInflater, R.layout.list_articles, parent, false);

        return new ArticleViewHolder(articlesBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleRecyclerAdapter.ArticleViewHolder holder, int position) {

        Articles articles = mArticles.get(position);

        holder.articlesBinding.setArticlesModel(articles);
        holder.articlesBinding.setImageUrl(articles.getUrlToImage());
        holder.mCurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder{

        public ListArtclesBinding articlesBinding;
        public int mCurrentPosition;

        public ArticleViewHolder(@NonNull ListArtclesBinding itemView) {
            super(itemView.getRoot());
            this.articlesBinding = itemView;

            articlesBinding.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Articles articles = mArticles.get(mCurrentPosition);
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("webview", articles.getUrl());
                    mContext.startActivity(intent);
                }
            });
        }


    }

}























