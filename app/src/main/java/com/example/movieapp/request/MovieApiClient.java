package com.example.movieapp.request;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.movieapp.model.Movie;
import com.example.movieapp.response.MovieSearchResponse;
import com.example.movieapp.utils.Credentials;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieApiClient {

    //LiveData
    private MutableLiveData<List<Movie>> movieMutableLiveData;

    private static MovieApiClient instance;


    public static MovieApiClient getInstance(){
        if (instance == null){
            instance =new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        movieMutableLiveData= new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMovies(){ 
        return movieMutableLiveData ;
    }

    // 1- This method that we are going to call through the classes
    public void searchMovieApi(String query, int pageNumber){
        getMovies(query, pageNumber);
    }

    // Search method query
    private void getMovies(String query, int pageNumber){
         Service.getMoviesApi().searchMovie(Credentials.API_KEY, query, pageNumber)
        .enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                movieMutableLiveData.postValue(response.body().getMovieList());
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                Log.v("you", " error ");
            }
        });
    }

}






























// making Global Runnable
//private RetrieveMovieRunnable retrieveMovieRunnable;

//    public LiveData<List<Movie>> getMovies(){
////        retrieveMovieRunnable = new RetrieveMovieRunnable("fast", 1);
////                retrieveMovieRunnable.getMovies("fast",1);
//        return movieMutableLiveData ;
//    }


//    // 1- This method that we are going to call through the classes
//    public void searchMovieApi(String query, int pageNumber){
//        getMovies(query, pageNumber);
//
////        if (retrieveMovieRunnable != null){
////            retrieveMovieRunnable =null;
////        }
//
////        retrieveMovieRunnable = new RetrieveMovieRunnable(query, pageNumber);
////        retrieveMovieRunnable.run();
//
////        final Future myHandler  = AppExecutor.getInstance().scheduledExecutorService().submit(retrieveMovieRunnable);
////
////        AppExecutor.getInstance().scheduledExecutorService().schedule(new Runnable() {
////            @Override
////            public void run() {
////                // Canceling the retrofit call
////                myHandler.cancel(true);
////            }
////        },3000, TimeUnit.MILLISECONDS);
   // }










//Retrieve data from api by Runnable class
// We have 2 types of queries : the ID & search Query

//    private class RetrieveMovieRunnable implements Runnable{
//        private String query;
//        private int pageNumber;
//        private boolean cancelRequest;
//
//        public RetrieveMovieRunnable(String query, int pageNumber) {
//            this.query = query;
//            this.pageNumber = pageNumber;
//            cancelRequest = false;
//        }
//
//        @Override
//        public void run() {
//            // Getting the response object
//            try {
//                Response response = getMovies(query, pageNumber).execute();
//                if (cancelRequest){
//                    return;
//                }
//                if (response.code() == 200){
//                    Log.v("you", " 200 ");
//                    List<Movie> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovieList());
//                    if (pageNumber ==1){
//                        // sending data to live data
//                        // postvalue : used for background thread
//                        // setvalue : not for background thread
//                        movieMutableLiveData.postValue(list);
//
//                    }
//                    else {
//                        List<Movie> currentMovies = movieMutableLiveData.getValue();
//                        currentMovies.addAll(list);
//                        movieMutableLiveData.postValue(currentMovies);
//                    }
//                }
//                else {
//                    String error = response.errorBody().string();
//                    Log.v("Tag", " Error " + error);
//                    Log.v("you", " 200 ");
//                    movieMutableLiveData.postValue(null);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                movieMutableLiveData.postValue(null);
//            }
//
//
//
//        }
//
//        // Search method query
//        private Call<MovieSearchResponse> getMovies(String query, int pageNumber){
//            return Service.getMoviesApi().searchMovie(Credentials.API_KEY, query, pageNumber);
//        }
//
//        private void setCancelRequest(){
//            Log.v("Tag", "Cancelling Search Request");
//        }
//    }

