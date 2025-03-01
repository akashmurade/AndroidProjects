package com.example.movieapp.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.R;
import com.example.movieapp.services.MovieApiServices;
import com.example.movieapp.services.RetrofitInstance;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MovieRepository {
    private static final String TAG = "MovieRepository";
    private MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
    private final Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMoviesLiveData() {
        Log.d(TAG, "Fetching movies from API...");

        MovieApiServices movieApiServices = RetrofitInstance.getService();
        Call<Result> call = movieApiServices.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d(TAG, "API Response received. Checking status...");

                if (response.isSuccessful()) {
                    Log.d(TAG, "Response successful. HTTP Code: " + response.code());

                    // Log the raw JSON response
                    try {
                        String rawJson = new Gson().toJson(response.body());
                        Log.d(TAG, "Raw JSON Response: " + rawJson);
                    } catch (Exception e) {
                        Log.e(TAG, "Error parsing JSON: " + e.getMessage());
                    }

                    Result result = response.body();
                    if (result != null && result.getResults() != null) {
                        List<Movie> movies = result.getResults();
                        Log.d(TAG, "Movies fetched: " + movies.size());
                        moviesLiveData.postValue(movies);
                    } else {
                        Log.e(TAG, "Response body is null or empty.");
                    }
                } else {
                    Log.e(TAG, "API Response Error. Code: " + response.code() + ", Message: " + response.message());
                    try {
                        Log.e(TAG, "Error body: " + response.errorBody().string());
                    } catch (Exception e) {
                        Log.e(TAG, "Error reading error body: " + e.getMessage());
                    }
                }
            }


            @Override
            public void onFailure(Call<Result> call, Throwable throwable) {
                Log.e(TAG, "API Request Failed: " + throwable.getMessage());
            }
        });

        return moviesLiveData;
    }
}
