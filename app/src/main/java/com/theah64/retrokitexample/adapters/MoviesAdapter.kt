package com.theah64.retrokitexample.adapters

import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.theah64.retrokit.adapters.BaseButterKnifeRecyclerViewHolder
import com.theah64.retrokit.adapters.BaseRecyclerViewAdapter
import com.theah64.retrokitexample.R
import com.theah64.retrokitexample.model.Movie

/**
 * Created by theapache64 on 26/8/17.
 */

class MoviesAdapter(data: List<Movie>, callback: BaseRecyclerViewAdapter.Callback<Movie>?) : BaseRecyclerViewAdapter<MoviesAdapter.ViewHolder, Movie>(data, callback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int, item: Movie) {
        holder.tvMovieTitle!!.text = item.title
        holder.tvMovieDescription!!.text = item.description
    }

    override fun getRowLayoutID(): Int {
        return R.layout.movie_row
    }

    override fun getNewRow(row: View): ViewHolder {
        return ViewHolder(row, this)
    }

    inner class ViewHolder(row: View, adapter: BaseRecyclerViewAdapter<*, *>) : BaseButterKnifeRecyclerViewHolder<Movie>(row, adapter) {

        @BindView(R.id.tvMovieTitle)
        lateinit var tvMovieTitle: TextView

        @BindView(R.id.tvMovieDescription)
        lateinit var tvMovieDescription: TextView
    }
}
