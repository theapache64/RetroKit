package com.theah64.retrokitexample.model

import com.theah64.retrokit.activities.BaseAppCompatActivity
import com.theah64.retrokit.models.SimpleModel

/**
 * Created by theapache64 on 24/8/17.
 */

class MenuItem(title: String, subtitle: String, val classToLaunch: Class<out BaseAppCompatActivity>) : SimpleModel(null, title, subtitle)
