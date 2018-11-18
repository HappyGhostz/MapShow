package com.zcp.mapshow.ui

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.zcp.mapshow.R
import com.zcp.mapshow.ui.base.BaseActivity
import org.jetbrains.anko.toast

class HomeActivity : BaseActivity() {
    override fun getContentView(): Int = R.layout.activity_home

    override fun initview() {
        val token = FirebaseInstanceId.getInstance().getToken()
        toast("is$token")
        Log.e("token",token+"error")
        FirebaseMessaging.getInstance().subscribeToTopic("MapShow").addOnCompleteListener({
//            toast(FirebaseMessaging.INSTANCE_ID_SCOPE)
        })
    }

    override fun initData() {

    }
}
