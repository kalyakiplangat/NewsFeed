package com.example.newsfeed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsfeed.R;
import com.example.newsfeed.sourcemodel.Source;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context mContext;
    private List<Source> mCategories = new ArrayList<>();

    public CategoryAdapter(Context mContext, List<Source> mCategories) {
        this.mContext = mContext;
        this.mCategories = mCategories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.source_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Source category = mCategories.get(position);

        holder.sourceName.setText("Source : " + category.getName().substring(0, 1).toUpperCase() + category.getName().substring(1).toLowerCase());
        holder.description.setText(category.getDescription());
        holder.category.setText("Category : " + category.getCategory().substring(0, 1).toUpperCase() + category.getCategory().substring(1).toLowerCase());

    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        public TextView sourceName;
        public TextView description;
        public TextView category;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            sourceName = itemView.findViewById(R.id.source_text);
            description = itemView.findViewById(R.id.description_text);
            category = itemView.findViewById(R.id.category_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
