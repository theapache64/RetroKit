package com.theah64.retrokitexample.rest.responses;

import com.google.gson.annotations.SerializedName;
import com.theah64.retrokitexample.model.User;

/**
 * Created by theapache64 on 25/8/17.
 */

public class GetUserProfileResponse extends BaseAPIResponse {

    @SerializedName("user")
    private final User user;

    public GetUserProfileResponse(boolean error, String message, User user) {
        super(error, message);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
