package com.zenaufa.popularmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.zenaufa.popularmovie.model.Movie;

public class DetailActivity extends AppCompatActivity {

    Movie movie;
    TextView title;
    TextView Release;
    TextView Description;
    TextView review;
    ImageView Poster;
    Button favorite;
    int id;
    RatingBar Rating;
    TextView Rating_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent.hasExtra("id")){
            String sDariMocieView = intent.getStringExtra("id");
            movie = new GsonBuilder().create().fromJson(sDariMocieView, Movie.class);
            Log.d("","");

            Poster = (ImageView) findViewById(R.id.poster);
            title = (TextView) findViewById(R.id.title);
            Release = (TextView) findViewById(R.id.releaseDate);
            Description = (TextView) findViewById(R.id.Description);
            Description.setMovementMethod(new ScrollingMovementMethod());
            Rating_text = (TextView) findViewById(R.id.rating_text);
            review = (TextView) findViewById(R.id.review);

            Picasso.with(this)
                    .load("http://image.tmdb.org/t/p/w185/" + intent.getStringExtra("movieImg"))
                    .placeholder(R.mipmap.ic_launcher)
                    .into(Poster);
            title.setText(intent.getStringExtra("movieTitle"));
            Release.setText(intent.getStringExtra("movieRelease"));
            review.setText(intent.getStringExtra("movieReview"));
            Double voteAverage = intent.getDoubleExtra("movieRating", 12);
            id = intent.getIntExtra("movieId", 0);

            //rating
            String rating = String.valueOf(voteAverage);
            Rating_text.setText(rating);

            //description
            Description.setText(intent.getStringExtra("movieDesc"));

            //actionbar
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("Movie Details");
            startService(intent);


        }
    }


}
