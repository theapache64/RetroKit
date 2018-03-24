package com.theah64.retrokitexample.model.data

import com.google.gson.annotations.SerializedName
import com.theah64.retrokitexample.model.Movie

/**
 * Created by theapache64 on 25/8/17.
 */

class GetMoviesData(@field:SerializedName("movies")
                    val movies: List<Movie>)
