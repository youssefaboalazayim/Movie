package com.example.movieapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.model.Movie;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> movieList ;

    public MovieRecyclerView(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    private OnMovieListener onMovieListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_layout , parent,false);
        return new MovieViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((MovieViewHolder)holder).title.setText(movieList.get(position).getTitle());
        ((MovieViewHolder)holder).release_date.setText(movieList.get(position).getRelease_date());
        ((MovieViewHolder)holder).duration.setText(movieList.get(position).getVote_average()+ "");

        // vote average is over 10 and our rating bar over 5 dividing 2
        ((MovieViewHolder)holder).ratingBar.setRating((movieList.get(position).getVote_average())/2);

        // Image view using glide library

        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/"+movieList.get(position).getPoster_path())
                .into((((MovieViewHolder)holder).imageView));

    }

    @Override
    public int getItemCount() {
        if (movieList != null){
            return movieList.size();
        }
        return 0;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }
}
