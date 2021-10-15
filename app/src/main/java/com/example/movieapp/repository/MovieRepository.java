package com.example.movieapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.model.Movie;
import com.example.movieapp.request.MovieApiClient;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;

   private MovieApiClient movieApiClient;


    public static MovieRepository getInstance(){
        if (instance == null){
            instance = new MovieRepository();
        }
        return instance;
    }


    private MovieRepository(){
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<Movie>> getMovies(){
        return movieApiClient.getMovies();
    }


    // 2-Calling the method in Repository
    public void searchMovieApi(String query, int pageNumber){
        movieApiClient.searchMovieApi(query, pageNumber);
    }
}
