package com.theah64.retrokitexample.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.theah64.retrokit.activities.BaseAppCompatActivity;
import com.theah64.retrokit.models.SimpleModel;
import com.theah64.retrokitexample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseAppCompatActivity {

    @BindView(R.id.rvMenuItems)
    RecyclerView rvMenuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentViewWithButterKnife(R.layout.activity_main);


        final List<? extends SimpleModel> menuItems = new ArrayList<>();
        

    }
}
