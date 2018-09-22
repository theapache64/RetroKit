package com.theah64.retrokitexample.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.evrencoskun.tableview.TableView;
import com.theah64.retrokit.activities.BaseDynamicActivity;
import com.theah64.retrokit.activities.BaseDynamicTableActivity;
import com.theah64.retrokit.retro.BaseAPIResponse;
import com.theah64.retrokitexample.R;
import com.theah64.retrokitexample.model.Movie;
import com.theah64.retrokitexample.model.data.GetMoviesData;
import com.theah64.retrokitexample.rest.APIInterface;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

public class TableActivity extends BaseDynamicTableActivity<GetMoviesData, Movie, APIInterface> {

    @BindView(R.id.tvMovies)
    TableView tvMovies;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        this.setup();
    }

    @Override
    protected List<Movie> getRowModels(GetMoviesData response) {
        return response.getMovies();
    }

    @Override
    protected Object[] getRow(Movie movie) {
        return new Object[]{
                movie.getTitle(),
                movie.getDescription()
        };
    }

    @Override
    protected int[] getHeaders() {
        return new int[]{
                R.string.Sl_No,
                R.string.Title,
                R.string.Description
        };
    }

    @Override
    protected TableView getTableView() {
        return tvMovies;
    }

    @Override
    protected Call<BaseAPIResponse<GetMoviesData>> getCall(APIInterface apiInterface) {
        return apiInterface.getMovies();
    }

    @Override
    public int getMainViewID() {
        return R.id.tvMovies;
    }
}
