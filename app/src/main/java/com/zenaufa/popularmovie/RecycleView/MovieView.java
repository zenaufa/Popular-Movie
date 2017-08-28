package com.zenaufa.popularmovie.RecycleView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.zenaufa.popularmovie.DetailActivity;
import com.zenaufa.popularmovie.R;
import com.zenaufa.popularmovie.model.Movie;
import com.zenaufa.popularmovie.model.Movies;

import java.util.List;


/**
 * Created by Zen Aufa on 19/08/2017.
 */

public class MovieView extends RecyclerView.Adapter<com.zenaufa.popularmovie.RecycleView.MovieView.MoviesHolder>
{
    Context context;
    List<Movie> MovieView;

    public MovieView(List<Movie> MovieView){
        this.MovieView = MovieView;
    }


    @Override
    public MoviesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.activity_main2, parent, false);
        return new MoviesHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesHolder holder, int position) {
        final Movie result = MovieView.get(position);
        String url = "http://image.tmdb.org/t/p/w185" + result.getPosterPath();
        Picasso.with(context).load(url).fit().centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.poster);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = new GsonBuilder().create().toJson(result);

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", s);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return MovieView.size();
    }

    static class MoviesHolder extends RecyclerView.ViewHolder{
        ImageView poster;
        public MoviesHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.poster);
        }
    }
}
