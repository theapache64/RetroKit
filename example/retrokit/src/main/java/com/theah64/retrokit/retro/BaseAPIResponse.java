package com.theah64.retrokit.retro;

import com.google.gson.annotations.SerializedName;

/**
 * Created by theapache64 on 25/8/17.
 */

public class BaseAPIResponse<D> {

    @SerializedName("error")
    private final boolean error;

    @SerializedName("message")
    private final String message;

    @SerializedName("data")
    D data;

    public BaseAPIResponse(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
