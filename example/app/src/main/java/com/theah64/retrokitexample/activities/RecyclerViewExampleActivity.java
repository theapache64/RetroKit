package com.theah64.retrokitexample.activities;

import android.os.Bundle;

import com.theah64.retrokit.activities.BaseRecyclerViewActivity;
import com.theah64.retrokitexample.R;

public class RecyclerViewExampleActivity extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);
    }

    @Override
    protected int getRecyclerViewID() {
        return 0;
    }
}
