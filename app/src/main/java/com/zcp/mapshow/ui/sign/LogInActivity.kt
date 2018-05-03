package com.zcp.mapshow.ui.sign

import android.graphics.drawable.AnimationDrawable
import com.zcp.mapshow.R
import com.zcp.mapshow.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.backgroundDrawable

/**
 * @author Zhao Chenping
 * @creat 2018/5/3.
 * @description
 */
class LogInActivity:BaseActivity() {

    override fun getContentView(): Int  = R.layout.activity_login

    override fun initview() {
        //看到一个别人实现帧动画的思路，就是将所有的图片放到arrays文件里，动态获取，然后根据
        //设置的播放时间，是否循环等条件动态设置imageView的背景，这里用到了异步线程，要考虑到内存泄漏的问题
        //https://github.com/ansen360/FrameAnimation
        val drawable = resources.getDrawable(R.drawable.login_tab_animation_list) as AnimationDrawable
        tab.backgroundDrawable = drawable
        drawable.start()
        //在这里也可以用fresco加载gif，但我需要的是透明的 ，找不到透明gif就只好用上面的方法了
//        val tabView = find<SimpleDraweeView>(R.id.tab)
//        val builder = Fresco.newDraweeControllerBuilder()
//                .setUri("res:// /" + R.mipmap.log_tab)
//                .setAutoPlayAnimations(true)
//                .build()
//        tabView.controller = builder
    }

    override fun initData() {

    }
}