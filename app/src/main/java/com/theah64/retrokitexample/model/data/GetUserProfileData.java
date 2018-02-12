package com.theah64.retrokitexample.model.data;

import com.google.gson.annotations.SerializedName;
import com.theah64.retrokitexample.model.User;

/**
 * Created by theapache64 on 25/8/17.
 */

public class GetUserProfileData {

    @SerializedName("user")
    private final User user;

    public GetUserProfileData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
