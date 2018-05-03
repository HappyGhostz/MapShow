package com.zcp.mapshow.ui.sign

import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.zcp.mapshow.R
import com.zcp.mapshow.ui.base.BaseActivity
import org.jetbrains.anko.find

/**
 * @author Zhao Chenping
 * @creat 2018/5/3.
 * @description
 */
class LogInActivity:BaseActivity() {

    override fun getContentView(): Int  = R.layout.activity_login

    override fun initview() {
        val tabView = find<SimpleDraweeView>(R.id.tab)
        val builder = Fresco.newDraweeControllerBuilder()
                .setUri("res:// /" + R.mipmap.tab)
                .setAutoPlayAnimations(true)
                .build()
        tabView.controller = builder
    }

    override fun initData() {

    }
}