package com.theah64.retrokitexample.model

import com.google.gson.annotations.SerializedName

/**
 * Created by theapache64 on 25/8/17.
 */

class Movie(@field:SerializedName("title")
            val title: String, @field:SerializedName("overview")
            val description: String)
