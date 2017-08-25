package com.theah64.retrokitexample.rest.responses;

import com.google.gson.annotations.SerializedName;
import com.theah64.retrokit.retro.BaseAPIResponse;
import com.theah64.retrokitexample.model.data.GetUserProfileData;

/**
 * Created by theapache64 on 25/8/17.
 */

public class GetUserProfileResponse extends BaseAPIResponse<GetUserProfileData> {

    @SerializedName("data")
    private final GetUserProfileData data;

    public GetUserProfileResponse(boolean error, String message, GetUserProfileData data) {
        super(error, message);
        this.data = data;
    }

    public GetUserProfileData getData() {
        return data;
    }
}
