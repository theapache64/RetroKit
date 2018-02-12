package com.theah64.retrokit.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by theapache64 on 2/1/18.
 */

public class BasePaginationResponse {

    @SerializedName("total_results")
    private final int totalResults;

    @SerializedName("items_per_page")
    private final int itemsPerPage;

    public BasePaginationResponse(int totalResults, int itemsPerPage) {
        this.totalResults = totalResults;
        this.itemsPerPage = itemsPerPage;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }
}
