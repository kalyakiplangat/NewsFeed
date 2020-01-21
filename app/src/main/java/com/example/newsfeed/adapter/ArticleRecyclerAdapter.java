package com.example.newsfeed.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsfeed.R;
import com.example.newsfeed.activity.DetailActivity;
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
        View itemView = mLayoutInflater.inflate(R.layout.list_articles, parent,false);
        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleRecyclerAdapter.ArticleViewHolder holder, int position) {
        Articles articles = mArticles.get(position);

        Glide.with(mContext).load(mArticles.get(position).getUrlToImage()).into(holder.image);
        holder.title.setText(articles.getTitle());
        holder.description.setText(articles.getDescription());
        holder.author.setText(articles.getAuthor());
        holder.date.setText(articles.getPublishedAt());

        holder.mCurrentPosition = position;

    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView title;
        public TextView description;
        public TextView author;
        public TextView date;
        public int mCurrentPosition;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            title = (TextView) itemView.findViewById(R.id.headline_text);
            description = (TextView) itemView.findViewById(R.id.description_text);
            author = (TextView) itemView.findViewById(R.id.author_text);
            date = (TextView) itemView.findViewById(R.id.date_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Articles articles = mArticles.get(mCurrentPosition);
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("extras", mCurrentPosition);

                    intent.putExtra("image", articles.getUrlToImage());
                    intent.putExtra("title", articles.getTitle());
                    intent.putExtra("url", articles.getUrl());
                    intent.putExtra("author", articles.getAuthor());
                    intent.putExtra("date", articles.getPublishedAt());

                    mContext.startActivity(intent);
                }
            });
        }
    }

}























