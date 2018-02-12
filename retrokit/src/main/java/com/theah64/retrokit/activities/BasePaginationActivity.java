package com.theah64.retrokit.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.theah64.retrokit.api.response.BasePaginationResponse;
import com.theah64.retrokit.retro.BaseAPIResponse;

import org.json.JSONException;

import retrofit2.Call;

/**
 * Created by theapache64 on 2/1/18.
 */

public abstract class BasePaginationActivity<MODEL, RESPONSE, APIINTERFACE> extends BaseSearchableActivity<MODEL, RESPONSE, APIINTERFACE> {

    private static final String X = BasePaginationActivity.class.getName();
    int pastVisibleItems, visibleItemCount, totalItemCount;
    private int pageNo = 1;
    private int totalPages = -1;
    private int totalItems = -1, itemsPerPage = -1;
    private boolean isLoading = false;

    @Override
    protected void setup() {

        final RecyclerView rv = getRecyclerView();
        final LinearLayoutManager llm = (LinearLayoutManager) rv.getLayoutManager();
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {

                    visibleItemCount = llm.getChildCount();
                    totalItemCount = llm.getItemCount();
                    pastVisibleItems = llm.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {

                        if (!isLoading) {
                            isLoading = true;
                            Log.v(X, "Scrolled to the bottom!!!");


                            //Do pagination.. i.e. fetch new data
                            try {
                                totalPages = getTotalPages();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                throw new IllegalArgumentException(e.getMessage());
                            }

                            if (pageNo < totalPages) {

                                Log.d(X, "Has more pages");
                                pageNo++;
                                System.out.println("Page is " + pageNo);
                                loadData(false);
                            } else {
                                Log.d(X, "No more pageNo");
                            }
                        }
                    }
                }
            }
        });


        super.setup();
    }

    @Override
    protected void onSuccess(RESPONSE response, boolean isClearList) {
        isLoading = false;
        super.onSuccess(response, isClearList);
    }

    private int getTotalPages() throws JSONException {

        setPaginationDetails((BasePaginationResponse) getResponse());


        int totalPages = (int) Math.ceil((float) totalItems / itemsPerPage);
        Log.d(X, "Total pages : " + totalPages);
        Log.d(X, "Total items : " + totalItems);
        Log.d(X, "items per pageNo : " + itemsPerPage);
        return totalPages;
    }

    public void setPaginationDetails(BasePaginationResponse response) throws JSONException {
        this.totalItems = response.getTotalResults();
        this.itemsPerPage = response.getItemsPerPage();
    }

    @Override
    protected Call<BaseAPIResponse<RESPONSE>> getCall(APIINTERFACE apiInterface) {
        return getCall(apiInterface, pageNo);
    }

    protected abstract Call<BaseAPIResponse<RESPONSE>> getCall(APIINTERFACE apiInterface, int pageNo);


}
