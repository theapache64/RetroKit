package com.theah64.retrokitexample.model.data

import com.google.gson.annotations.SerializedName
import com.theah64.retrokitexample.model.User

/**
 * Created by theapache64 on 25/8/17.
 */

class GetUserProfileData(@field:SerializedName("user")
                         val user: User)
