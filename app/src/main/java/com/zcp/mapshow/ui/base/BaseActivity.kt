package com.zcp.mapshow.ui.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.WindowManager
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * @author Zhao Chenping
 * @creat 2018/5/2.
 * @description
 */
abstract class BaseActivity :RxAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setFullScreenForActivity()
        super.onCreate(savedInstanceState)
        initStatus()
        setContentView(getContentView())
        initview()
        initData()
    }

    abstract fun setFullScreenForActivity()
    abstract fun getContentView(): Int
    abstract fun initview()
    abstract fun initData()

    fun initActionBar(toolBar : Toolbar, title:String, hasHomeEnable:Boolean){
        toolBar.title = title
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(hasHomeEnable)

    }
    fun <T>bindRxLifecycle(): LifecycleTransformer<T> {
        return this.bindToLifecycle<T>()
    }
    protected fun initStatus() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            // 部分机型的statusbar会有半透明的黑色背景
            //设置5.0状态栏透明
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}