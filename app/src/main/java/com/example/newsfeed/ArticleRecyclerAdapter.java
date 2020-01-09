package com.example.newsfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.ArticleViewHolder> {
    private Context mContext;
    private ArrayList<String> mArticles;
    private LayoutInflater mLayoutInflater;

    //class constructor
    public ArticleRecyclerAdapter(){}


    public ArticleRecyclerAdapter(Context context, ArrayList<String> articles){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mArticles = articles;
    }



    @Override
    public ArticleRecyclerAdapter.ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.list_articles, parent,false);
        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleRecyclerAdapter.ArticleViewHolder holder, int position) {
        holder.headlines.setText(mArticles.get(position));
        holder.description.setText(mArticles.get(position));
        holder.date.setText(mArticles.get(position));
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView headlines;
        public TextView description;
        public TextView date;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            headlines = (TextView) itemView.findViewById(R.id.txtHeadlines);
            description = (TextView) itemView.findViewById(R.id.txtDescription);
            date = (TextView) itemView.findViewById(R.id.txtDate);
        }
    }
}
