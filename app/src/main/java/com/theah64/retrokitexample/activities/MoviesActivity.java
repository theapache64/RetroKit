package com.theah64.retrokitexample.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.theah64.retrokit.activities.BaseRecyclerViewActivity;
import com.theah64.retrokit.adapters.BaseRecyclerViewAdapter;
import com.theah64.retrokit.retro.BaseAPIResponse;
import com.theah64.retrokitexample.R;
import com.theah64.retrokitexample.adapters.MoviesAdapter;
import com.theah64.retrokitexample.model.Movie;
import com.theah64.retrokitexample.model.data.GetMoviesData;
import com.theah64.retrokitexample.rest.APIInterface;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

public class MoviesActivity extends BaseRecyclerViewActivity<Movie, GetMoviesData, APIInterface> implements BaseRecyclerViewAdapter.Callback<Movie> {


    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);
        enableBackNavigationWithTitle("RecyclerView example");
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        setup();
    }


    @Override
    public int getMainViewID() {
        return R.id.rvMovies;
    }

    @Override
    protected String getActionBarSubTitle() {
        return null;
    }

    @Override
    protected String getActionBarTitle() {
        return null;
    }

    @Override
    protected String getLoadingMessage() {
        return "Loading movies";
    }


    @Override
    protected Call<BaseAPIResponse<GetMoviesData>> getCall(APIInterface apiInterface) {
        return apiInterface.getMovies();
    }

    @Override
    public void onItemClick(Movie movie, int position) {

    }

    @Override
    protected RecyclerView getRecyclerView() {
        return rvMovies;
    }

    @Override
    public List<Movie> getData(GetMoviesData data) {
        return data.getMovies();
    }

    @Override
    public BaseRecyclerViewAdapter getNewAdapter(List<Movie> data) {
        return new MoviesAdapter(data, this);
    }
}
