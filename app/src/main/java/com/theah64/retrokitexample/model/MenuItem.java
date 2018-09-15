package com.theah64.retrokitexample.model;

import com.theah64.retrokit.activities.BaseAppCompatActivity;
import com.theah64.retrokit.models.SimpleModel;

/**
 * Created by theapache64 on 24/8/17.
 */

public class MenuItem extends SimpleModel {

    private final Class<? extends BaseAppCompatActivity> classToLaunch;

    public MenuItem(String title, String subtitle, Class<? extends BaseAppCompatActivity> classToLaunch) {
        super(null, title, subtitle);
        this.classToLaunch = classToLaunch;
    }

    public Class<? extends BaseAppCompatActivity> getClassToLaunch() {
        return classToLaunch;
    }
}
