package com.theah64.retrokitexample.adapters;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.theah64.retrokit.adapters.BaseButterknifeRecyclerViewHolder;
import com.theah64.retrokit.adapters.BaseRecyclerViewAdapter;
import com.theah64.retrokitexample.R;
import com.theah64.retrokitexample.model.Movie;

import java.util.List;

import butterknife.BindView;

/**
 * Created by theapache64 on 26/8/17.
 */

public class MoviesAdapter extends BaseRecyclerViewAdapter<MoviesAdapter.ViewHolder, Movie> {

    public MoviesAdapter(List<Movie> data, @Nullable Callback<Movie> callback) {
        super(data, callback);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, Movie item) {
        holder.tvMovieTitle.setText(item.getTitle());
        holder.tvMovieDescription.setText(item.getDescription());
    }

    @Override
    protected int getRowLayoutID() {
        return R.layout.movie_row;
    }

    @Override
    protected ViewHolder getNewRow(View row) {
        return new ViewHolder(row, this);
    }

    class ViewHolder extends BaseButterknifeRecyclerViewHolder<Movie> {

        @BindView(R.id.tvMovieTitle)
        TextView tvMovieTitle;

        @BindView(R.id.tvMovieDescription)
        TextView tvMovieDescription;

        public ViewHolder(View row, BaseRecyclerViewAdapter adapter) {
            super(row, adapter);
        }
    }
}
