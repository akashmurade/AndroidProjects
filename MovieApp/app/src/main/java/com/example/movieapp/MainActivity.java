package com.example.movieapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.movieapp.databinding.ActivityMainBinding;
import com.example.movieapp.model.Movie;
import com.example.movieapp.view.MovieAdapter;
import com.example.movieapp.viewmodel.MovieViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private MovieViewModel movieViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ActivityMainBinding binding;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        // ✅ Initialize RecyclerView with Empty List
        recyclerView = binding.recyclerView;
        movies = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, movies);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);

        getPopularMovies(); // Fetch movies

        swipeRefreshLayout = binding.swipeLayout;
        swipeRefreshLayout.setColorSchemeColors(R.color.green);
        swipeRefreshLayout.setOnRefreshListener(() -> getPopularMovies());
    }

    private void getPopularMovies() {
        Log.d("Movies", "Fetching movies from ViewModel..."); // Debug Log

        movieViewModel.getMovies().observe(this, moviesFromLiveData -> {
            if (moviesFromLiveData != null) {
                Log.d("Movies", "Movies fetched: " + moviesFromLiveData.size()); // Log movie count

                movies = new ArrayList<>(moviesFromLiveData);
                displayMoviesInRecyclerView(); // ✅ Call here only if data exists
            } else {
                Log.e("Movies", "Movies list is null!");
            }
        });
    }


    private void displayMoviesInRecyclerView() {
        recyclerView = binding.recyclerView;

        // ✅ Ensure LayoutManager is set BEFORE the adapter
        if (recyclerView.getLayoutManager() == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        movieAdapter = new MovieAdapter(this, movies);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
    }

}