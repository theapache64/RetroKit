package com.theah64.retrokitexample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.theah64.retrokit.activities.BaseAppCompatActivity;
import com.theah64.retrokit.adapters.BaseRecyclerViewAdapter;
import com.theah64.retrokit.adapters.SimpleRecyclerViewAdapter;
import com.theah64.retrokit.models.SimpleModel;
import com.theah64.retrokitexample.R;
import com.theah64.retrokitexample.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseAppCompatActivity {

    @BindView(R.id.rvMenuItems)
    RecyclerView rvMenuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithButterKnife(R.layout.activity_main);

        //Adding menu items
        final List<SimpleModel> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("RecyclerView example", "Top rated movies", MoviesActivity.class));

        rvMenuItems.setLayoutManager(new LinearLayoutManager(this));
        rvMenuItems.setAdapter(new SimpleRecyclerViewAdapter(menuItems, new BaseRecyclerViewAdapter.Callback<SimpleModel>() {
            @Override
            public void onItemClick(SimpleModel simpleModel, int position) {
                final Intent i = new Intent(MainActivity.this, ((MenuItem) simpleModel).getClassToLaunch());
                startActivity(i);
            }
        }));

    }

}
