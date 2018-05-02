package com.zcp.mapshow.ui.base

import android.view.Window
import android.view.WindowManager

/**
 * @author Zhao Chenping
 * @creat 2018/5/2.
 * @description
 */
abstract class BaseFullSceenActivity:BaseActivity() {
    override fun setFullScreenForActivity() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}