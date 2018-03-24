package com.theah64.retrokitexample.model

import com.google.gson.annotations.SerializedName

/**
 * Created by theapache64 on 25/8/17.
 */

class User(@field:SerializedName("id")
           val id: String, @field:SerializedName("name")
           val name: String, @field:SerializedName("email")
           val email: String) {

    override fun toString(): String {
        return "User{" +
                "id='" + id + '\''.toString() +
                ", name='" + name + '\''.toString() +
                ", email='" + email + '\''.toString() +
                '}'.toString()
    }
}
