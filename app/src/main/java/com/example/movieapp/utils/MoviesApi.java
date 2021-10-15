package com.example.movieapp.utils;

import com.example.movieapp.model.Movie;
import com.example.movieapp.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesApi {

    // search for movie
    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page
    );


    // making search with id
    @GET("3/movie/{movie_id}?")
    Call<Movie> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );
}
