package com.theah64.retrokit.api.response;

import com.google.gson.annotations.SerializedName;
import com.theah64.retrokit.api.VersionChecker;

/**
 * Created by theapache64 on 12/9/17.
 */

public class VersionCheckerResponse {

    @SerializedName(VersionChecker.KEY_LATEST_VERSION_CODE)
    private final int latestVersionCode;

    public VersionCheckerResponse(int latestVersionCode) {
        this.latestVersionCode = latestVersionCode;
    }

    public int getLatestVersionCode() {
        return latestVersionCode;
    }
}
