package com.example.cocktailtestapp.views;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cocktailtestapp.R;
import com.example.cocktailtestapp.activitys.SearchActivity;
import com.example.cocktailtestapp.network.Drinks;
import com.example.cocktailtestapp.network.DrinksLite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

    private List<DrinksLite> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, List<Drinks> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            this.mData.add(new DrinksLite(data.get(i).getStrDrink(), data.get(i).getStrDrinkThumb()));
        }

    }
    public MyRecyclerViewAdapter(Context context, List<DrinksLite> data, int i) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DrinksLite drinksList = mData.get(position);
        holder.myTextView.setText(drinksList.getStrDrink());


        Glide
                .with(mInflater.getContext())
                .load(drinksList.getStrDrinkThumb())
                .into(holder.imageView);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.name_cocktail);

            imageView = itemView.findViewById(R.id.image_cocktail);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData.get(id).getStrDrink();
    }
    public String getImage(int id) {
        return mData.get(id).getStrDrinkThumb();
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}