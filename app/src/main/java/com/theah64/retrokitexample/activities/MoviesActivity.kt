package com.theah64.retrokitexample.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.theah64.retrokit.activities.BaseRecyclerViewActivity
import com.theah64.retrokit.adapters.BaseRecyclerViewAdapter
import com.theah64.retrokit.retro.BaseAPIResponse
import com.theah64.retrokitexample.R
import com.theah64.retrokitexample.adapters.MoviesAdapter
import com.theah64.retrokitexample.model.Movie
import com.theah64.retrokitexample.model.data.GetMoviesData
import com.theah64.retrokitexample.rest.APIInterface

import butterknife.BindView
import retrofit2.Call

class MoviesActivity : BaseRecyclerViewActivity<Movie, GetMoviesData, APIInterface>(), BaseRecyclerViewAdapter.Callback<Movie> {


    @BindView(R.id.rvMovies)
    lateinit var rvMovies: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_example)
        enableBackNavigationWithTitle("RecyclerView example")
        rvMovies!!.layoutManager = LinearLayoutManager(this)

        setup()
    }


    override fun getMainViewID(): Int {
        return R.id.rvMovies
    }

    override fun getActionBarSubTitle(): String? {
        return null
    }

    override fun getActionBarTitle(): String? {
        return null
    }

    override fun getLoadingMessage(): String {
        return "Loading movies"
    }


    override fun getCall(apiInterface: APIInterface): Call<BaseAPIResponse<GetMoviesData>> {
        return apiInterface.movies
    }

    override fun onItemClick(movie: Movie, position: Int) {

    }

    override fun getRecyclerView(): RecyclerView? {
        return rvMovies
    }

    override fun getData(data: GetMoviesData): List<Movie> {
        return data.movies
    }

    override fun getNewAdapter(data: List<Movie>): BaseRecyclerViewAdapter<*, *> {
        return MoviesAdapter(data, this)
    }
}
