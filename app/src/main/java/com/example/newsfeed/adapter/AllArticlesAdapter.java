package com.example.newsfeed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsfeed.R;
import com.example.newsfeed.model.basemodel.AllArticles;

import java.util.ArrayList;
import java.util.List;

public class AllArticlesAdapter extends RecyclerView.Adapter<AllArticlesAdapter.ArticleViewHolder> {
    private Context mContext;
    private List<AllArticles> mAllArticles = new ArrayList<>();

    public AllArticlesAdapter(Context mContext, List<AllArticles> mAllArticles) {
        this.mContext = mContext;
        this.mAllArticles = mAllArticles;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.articles_list, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        AllArticles articles = mAllArticles.get(position);

        Glide.with(mContext)
                .load(articles.getUrlToImage())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView);

        holder.title.setText(articles.getTitle());
        holder.description.setText(articles.getDescription());
        holder.author.setText(articles.getAuthor());
        holder.source.setText(articles.getSource().getName());
        holder.date.setText(articles.getPublishedAt());
        holder.mCurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return mAllArticles.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView description;
        public TextView source;
        public TextView author;
        public TextView date;
        public int mCurrentPosition;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title_text);
            description = itemView.findViewById(R.id.description);
            author = itemView.findViewById(R.id.author_text);
            date = itemView.findViewById(R.id.date_text);
            source = itemView.findViewById(R.id.source_text);
        }
    }
}
