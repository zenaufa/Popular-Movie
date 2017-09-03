package com.zenaufa.popularmovie;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zenaufa.popularmovie.RecycleView.MovieView;
import com.zenaufa.popularmovie.model.Movies;
import com.zenaufa.popularmovie.model.Movie;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Movies movieResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.posterGrid);

        new AsyncTask(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] params) {
                final String BASE_URL = "http://api.themoviedb.org/3/movie/";

                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd")
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();


                MovieEndPoint endPoint = retrofit.create(MovieEndPoint.class);
                try {
                    Call<Movies> call = endPoint.getPopular();
                    movieResults = (Movies) call.execute().body();

                    Log.d("","");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return endPoint;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                recyclerView.setAdapter(new MovieView(movieResults.getMovies()));
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
            }
        }.execute();
    }

    /*protected Retrofit retrofit() {
        final String BASE_URL = "http://api.themoviedb.org/3/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }*/


}
