package com.example.newsfeed.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.newsfeed.R;
import com.example.newsfeed.activity.DetailActivity;
import com.example.newsfeed.model.Articles;
import com.example.newsfeed.utils.Utils;

import java.util.List;


public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.ArticleViewHolder>{
    private Context mContext;
    private List<Articles> mArticles;

    //class constructor
    public ArticleRecyclerAdapter(Context context, List<Articles> articles){
        this.mContext = context;
        this.mArticles = articles;
    }

    @Override
    public ArticleRecyclerAdapter.ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_articles, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArticleRecyclerAdapter.ArticleViewHolder holder, int position) {

        Articles articles = mArticles.get(position);

        Glide.with(mContext)
                .load(articles.getUrlToImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView);

        holder.title.setText(articles.getTitle());
        holder.description.setText(articles.getDescription());
        holder.author.setText(articles.getAuthor());
        holder.source.setText(articles.getSource().getName());
        holder.date.setText(Utils.DateFormat(articles.getPublishedAt()));
        holder.mCurrentPosition = position;

    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public void setArticles(List<Articles> articles){
        this.mArticles = articles;
        notifyDataSetChanged();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView title;
        public TextView description;
        public TextView source;
        public TextView author;
        public TextView date;
        public ProgressBar progressBar;
        public int mCurrentPosition;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.headline_text);
            description = itemView.findViewById(R.id.description_text);
            author = itemView.findViewById(R.id.author_text);
            source = itemView.findViewById(R.id.source_text);
            date = itemView.findViewById(R.id.date_text);
            progressBar = itemView.findViewById(R.id.prograss_load_photo);


            itemView.setOnClickListener(new View.OnClickListener() {
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























