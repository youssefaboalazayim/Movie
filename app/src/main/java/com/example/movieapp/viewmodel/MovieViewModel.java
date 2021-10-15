package com.example.movieapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.model.Movie;
import com.example.movieapp.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {

    MovieRepository movieRepository;

    public MovieViewModel() {
        movieRepository= MovieRepository.getInstance();
    }

    public LiveData<List<Movie>> getMovies(){
        return movieRepository.getMovies();
    }

    // 3-Calling the method in view model
    public void searchMovieApi(String query, int pageNumber){
        movieRepository.searchMovieApi(query, pageNumber);
    }
}
