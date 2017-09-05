package com.zenaufa.popularmovie;

import com.zenaufa.popularmovie.model.Movies;
//import com.zenaufa.popularmovie.model.Movies;
//import com.zenaufa.popularmovie.model.TrailerResults;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Zen Aufa on 19/08/2017.
 */

public interface MovieEndPoint {
    @GET("popular?api_key=d778c6ec4dbeb10a05da504ad8f5f8ab")
    Call<Movies> getPopular();

   @GET("top_rated?api_key=d778c6ec4dbeb10a05da504ad8f5f8ab")
    Call<Movies> getTop();
//
//    @GET("/{id}/reviews?api_key=d778c6ec4dbeb10a05da504ad8f5f8ab")
//    Call<TrailerResults> getIdReview(@Path("id") int id);


}
