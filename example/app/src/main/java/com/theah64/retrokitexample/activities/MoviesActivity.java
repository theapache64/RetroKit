package com.theah64.retrokitexample.activities;

import android.os.Bundle;

import com.theah64.retrokit.activities.BaseRecyclerViewActivity;
import com.theah64.retrokit.retro.BaseAPIResponse;
import com.theah64.retrokitexample.R;
import com.theah64.retrokitexample.model.data.GetMoviesData;
import com.theah64.retrokitexample.rest.APIInterface;

import retrofit2.Call;

public class MoviesActivity extends BaseRecyclerViewActivity<GetMoviesData,APIInterface> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);
        enableBackNavigationWithTitle("RecyclerView example");
    }

    @Override
    protected int getRecyclerViewID() {
        return R.id.rvMovies;
    }

    @Override
    protected void onSuccess(GetMoviesData body) {

    }

    @Override
    protected int getMainViewID() {
        return 0;
    }

    @Override
    protected String getLoadingMessage() {
        return null;
    }

    @Override
    protected APIInterface getAPIInterface() {
        return null;
    }

    @Override
    protected Call<BaseAPIResponse<GetMoviesData>> getCall(APIInterface apiInterface) {
        return null;
    }


}
