package com.theah64.retrokitexample.activities

import android.os.Bundle
import android.widget.TextView

import com.theah64.retrokit.activities.BaseDynamicActivity
import com.theah64.retrokit.retro.BaseAPIResponse
import com.theah64.retrokitexample.R
import com.theah64.retrokitexample.model.User
import com.theah64.retrokitexample.model.data.GetUserProfileData
import com.theah64.retrokitexample.rest.APIInterface

import butterknife.BindView
import retrofit2.Call

class UserProfileActivity : BaseDynamicActivity<GetUserProfileData, APIInterface>() {

    @BindView(R.id.tvUserId)
    lateinit var tvUserId: TextView

    @BindView(R.id.tvUserName)
    lateinit var tvUserName: TextView

    @BindView(R.id.tvEmail)
    lateinit var tvEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        enableBackNavigationWithTitle("Profile")

        setup()
    }

    override fun getMainViewID(): Int {
        return R.id.llMain
    }

    override fun getActionBarSubTitle(): String? {
        return null
    }

    override fun getActionBarTitle(): String? {
        return null
    }

    override fun onSuccess(response: GetUserProfileData, isClearList: Boolean) {
        val user = response.user
        tvUserId!!.text = "#" + user.id
        tvUserName!!.text = user.name
        tvEmail!!.text = user.email
    }

    override fun getLoadingMessage(): String {
        return "Loading profile"
    }

    override fun getCall(apiInterface: APIInterface): Call<BaseAPIResponse<GetUserProfileData>> {
        return apiInterface.userProfile
    }
}
