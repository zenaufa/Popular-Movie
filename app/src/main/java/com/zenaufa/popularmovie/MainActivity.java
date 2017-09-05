package com.zenaufa.popularmovie;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zenaufa.popularmovie.RecycleView.MovieView;
import com.zenaufa.popularmovie.model.Movies;

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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.Popular:
                DisplayPopular();
                return true;

            case R.id.Top:
                DisplayTop();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.popular:
                    DisplayPopular();
                    return true;
                case R.id.top:
                    DisplayTop();
                    return true;
                case R.id.favorite:

                    return true;
            }
            return false;
        }

    };

    //Display Popular Movies
    public void DisplayPopular(){
        //setContentView(R.layout.activity_main);
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


    //Display Top Movies
    public void DisplayTop(){
        //setContentView(R.layout.activity_main);
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
                    Call<Movies> call = endPoint.getTop();
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
