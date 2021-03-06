package com.zcp.mapshow

import android.app.Application
import com.baidu.mapapi.SDKInitializer
//import com.hyphenate.chat.EMClient
//import com.hyphenate.chat.EMOptions
import android.app.ActivityManager
import android.content.Context
import android.util.Log
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import android.support.multidex.MultiDex
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.firebase.messaging.FirebaseMessaging


//import com.hyphenate.chat.EMClient.TAG


/**
 * @author Zhao Chenping
 * @creat 2018/4/28.
 * @description
 */
class AppAplication :Application(){
    override fun onCreate() {
        super.onCreate()
        //初始化Fresco
        Fresco.initialize(this)
        //初始化百度SDK
//        SDKInitializer.initialize(this)
        initIm()
    }

    private fun initIm() {
        var options = EMOptions()
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.acceptInvitationAlways = false
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.autoTransferMessageAttachments = true
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true)

        val pid = android.os.Process.myPid()
        val processAppName = getAppName(pid)
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null || !processAppName.equals(this.packageName, ignoreCase = true)) {
            Log.e("AppAplication", "enter the service process!")

            // 则此application::onCreate 是被service 调用的，直接返回
            return
        }
        //初始化
        EMClient.getInstance().init(this, options)
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true)

        initFcm()
    }

    private fun initFcm() {
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
    }

    private fun getAppName(pid: Int): String? {
        var processName: String? = null
        val am = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val l = am.runningAppProcesses
        val i = l.iterator()
//        val pm = this.packageManager
        while (i.hasNext()) {
            val info = i.next() as ActivityManager.RunningAppProcessInfo
            try {
                if (info.pid == pid) {
                    processName = info.processName
                    return processName
                }
            } catch (e: Exception) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }

        }
        return processName
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}