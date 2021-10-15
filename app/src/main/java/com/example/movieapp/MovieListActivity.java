package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.movieapp.adapter.MovieRecyclerView;
import com.example.movieapp.adapter.OnMovieListener;
import com.example.movieapp.model.Movie;
import com.example.movieapp.viewmodel.MovieViewModel;

import java.util.List;

public class  MovieListActivity extends AppCompatActivity implements OnMovieListener {

    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerAdapter;

    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        recyclerView =findViewById(R.id.recycler_view);



        // Calling the observing
        observeAnyChange();

        // Calling the configureRecyclerView
        configureRecyclerView();

        //Search Movie
        searchMovieApi("fast", 1);


    }


    // Observing any data change
    private void observeAnyChange(){
        movieViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (movies != null){
                    for (Movie movie : movies){
                        Log.v("Tag", "On Change " + movie.getTitle());
                        movieRecyclerAdapter.setMovieList(movies);
                    }
                }
            }
        });
    }

    //4- Calling the method in Main Activity
    private void searchMovieApi(String query, int pageNumber){
        movieViewModel.searchMovieApi(query, pageNumber);
    }

    // 5- Intializing recyclerview and add data to it
    private void configureRecyclerView(){
        movieRecyclerAdapter  = new MovieRecyclerView(this);
        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    // Implemented OnMovieListener Interface
    @Override
    public void onMovieClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {

    }
}