package com.example.movieapp.request;

import com.example.movieapp.utils.Credentials;
import com.example.movieapp.utils.MoviesApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {


    public static Retrofit.Builder retrofit_builder = new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = retrofit_builder.build();

    public static MoviesApi moviesApi = retrofit.create(MoviesApi.class);

    public static MoviesApi getMoviesApi(){
        return moviesApi;
    }
}
