package com.theah64.retrokitexample.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import com.theah64.retrokit.activities.BaseAppCompatActivity
import com.theah64.retrokit.adapters.BaseRecyclerViewAdapter
import com.theah64.retrokit.adapters.SimpleRecyclerViewAdapter
import com.theah64.retrokit.models.SimpleModel
import com.theah64.retrokitexample.R
import com.theah64.retrokitexample.model.MenuItem
import java.util.*

class MainActivity : BaseAppCompatActivity() {

    @BindView(R.id.rvMenuItems)
    lateinit var rvMenuItems: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Adding menu items
        val menuItems = ArrayList<SimpleModel>()
        menuItems.add(MenuItem("Dynamic activity example", "User profile", UserProfileActivity::class.java))
        menuItems.add(MenuItem("RecyclerView example", "Top rated movies", MoviesActivity::class.java))

        rvMenuItems.layoutManager = LinearLayoutManager(this)
        rvMenuItems.adapter = SimpleRecyclerViewAdapter(menuItems, BaseRecyclerViewAdapter.Callback { simpleModel, position ->
            val i = Intent(this@MainActivity, (simpleModel as MenuItem).classToLaunch)
            startActivity(i)
        })

    }

}
