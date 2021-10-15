package com.example.movieapp.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.AppExecutor;
import com.example.movieapp.model.Movie;
import com.example.movieapp.response.MovieSearchResponse;
import com.example.movieapp.utils.Credentials;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    //LiveData
    private MutableLiveData<List<Movie>> movieMutableLiveData;

    private static MovieApiClient instance;

    // making Global Runnable
    private RetrieveMovieRunnable retrieveMovieRunnable;

    public static MovieApiClient getInstance(){
        if (instance == null){
            instance =new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
//        MovieApiClient.getInstance();
    }

    public LiveData<List<Movie>> getMovies(){
        return movieMutableLiveData;
    }


    // 1- This method that we are going to call through the classes
    public void searchMovieApi(String query, int pageNumber){

        if (retrieveMovieRunnable != null){
            retrieveMovieRunnable =null;
        }

        retrieveMovieRunnable = new RetrieveMovieRunnable(query, pageNumber);

        final Future myHandler  = AppExecutor.getInstance().scheduledExecutorService().submit(retrieveMovieRunnable);

        AppExecutor.getInstance().scheduledExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // Canceling the retrofit call
                myHandler.cancel(true);
            }
        },3000, TimeUnit.MILLISECONDS);
    }

    //Retrieve data from api by Runnable class
    // We have 2 types of queries : the ID & search Query

    private class RetrieveMovieRunnable implements Runnable{
        private String query;
        private int pageNumber;
        private boolean cancelRequest;

        public RetrieveMovieRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            // Getting the response object
            try {
                Response response = getMovies(query, pageNumber).execute();
                if (cancelRequest){
                    return;
                }
                if (response.code() == 200){
                    List<Movie> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovieList());
                    if (pageNumber ==1){
                        // sending data to live data
                        // postvalue : used for background thread
                        // setvalue : not for background thread
                        movieMutableLiveData.postValue(list);

                    }
                    else {
                        List<Movie> currentMovies = movieMutableLiveData.getValue();
                        currentMovies.addAll(list);
                        movieMutableLiveData.postValue(currentMovies);
                    }
                }
                else {
                    String error = response.errorBody().string();
                    Log.v("Tag", " Error " + error);
                    movieMutableLiveData.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                movieMutableLiveData.postValue(null);
            }



        }

        // Search method query
        private Call<MovieSearchResponse> getMovies(String query, int pageNumber){
            return Service.getMoviesApi().searchMovie(Credentials.API_KEY, query, pageNumber);
        }

        private void setCancelRequest(){
            Log.v("Tag", "Cancelling Search Request");
        }
    }























}
