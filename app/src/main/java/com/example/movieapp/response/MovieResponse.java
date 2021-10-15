package com.example.movieapp.response;

import com.example.movieapp.model.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// This class for single movie request
public class MovieResponse {
    // 1- Finding the movie object
    @SerializedName("results")
    @Expose
    private Movie movie;
    public Movie getMovie(){
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
