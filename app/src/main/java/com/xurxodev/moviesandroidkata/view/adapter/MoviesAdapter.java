package com.xurxodev.moviesandroidkata.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter
        extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {


    public List<Movie> movies = new ArrayList<>();

    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movies, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.movieItem = movies.get(position);

        //int resizeHeight = holder.competitorImageView.getResources().getInteger(R.integer.image_competitor_resize_height);

        Picasso.with(holder.movieImageView.getContext())
                .load(holder.movieItem.getImage())
                //.resize(0, resizeHeight)
                .into(holder.movieImageView);

        holder.titleTextView.setText(holder.movieItem .getTitle());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView movieImageView;
        public final TextView titleTextView;

        public Movie movieItem;

        public ViewHolder(View view) {
            super(view);

            movieImageView = (ImageView) view.findViewById(R.id.item_movie_poster);
            titleTextView = (TextView) view.findViewById(R.id.item_movie_title);
        }
    }
}
