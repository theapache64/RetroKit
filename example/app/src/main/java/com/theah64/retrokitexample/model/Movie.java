package com.theah64.retrokitexample.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by theapache64 on 25/8/17.
 */

public class Movie {

    @SerializedName("title")
    private final String title;

    @SerializedName("overview")
    private final String description;

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
