package com.zcp.mapshow.ui

import android.graphics.Color
import com.example.happyghost.showtimeforkotlin.wegit.ProgressCustomView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.zcp.mapshow.R
import com.zcp.mapshow.ui.base.BaseFullSceenActivity
import com.zcp.mapshow.ui.sign.LogInActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit

/**
 * @author Zhao Chenping
 * @creat 2018/5/2.
 * @description
 */
class SplashActivity : BaseFullSceenActivity(){
    var isNext = false
    override fun initview() {

        initPushMessage()
        val splashView = find<SimpleDraweeView>(R.id.iv_spalsh)
        val draweeController = Fresco.newDraweeControllerBuilder()
                .setUri("res:// /" + R.mipmap.splash)
                .setAutoPlayAnimations(true)
                .build()
        splashView.controller = draweeController
    }

    private fun initPushMessage() {
        val extras = intent.extras
        if(extras!=null){
            val get = extras.getString("message")
            toast(get+"")
        }
    }

    override fun initData() {
        pc_downtime.startAnimation()
        pc_downtime.setPCviewOnClickListener(object : ProgressCustomView.PCviewOnClickListener{
            override fun onClick() {
                nextActivity()
            }
        })
        downTime(5)
                .compose(bindRxLifecycle())
                .subscribe(object :Observer<Int>{
                    override fun onComplete() {
                        nextActivity()
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: Int) {
                        pc_downtime.setmText("跳过:$t")
                        pc_downtime.setmTextColor(Color.parseColor("#000000"))
                    }

                    override fun onError(e: Throwable) {
                        nextActivity()
                    }
                })
    }

    private fun nextActivity() {
        if(!isNext){
            isNext=true
            startActivity<LogInActivity>()
            overridePendingTransition(R.anim.fade_entry,R.anim.fade_exit)
            finish()
        }
    }

    override fun getContentView(): Int =R.layout.activity_splash

    fun downTime(time:Int):Observable<Int>{
        return Observable.interval(0,1,TimeUnit.SECONDS)
                .map(Function<Long, Int> {
                    return@Function time-it.toInt()
                })
                .take((time+1).toLong())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

}