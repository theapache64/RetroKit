package com.theah64.retrokitexample.model.data;

import com.google.gson.annotations.SerializedName;
import com.theah64.retrokitexample.model.Movie;

import java.util.List;

/**
 * Created by theapache64 on 25/8/17.
 */

public class GetMoviesData {

    @SerializedName("movies")
    private final List<Movie> movies;

    public GetMoviesData(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
