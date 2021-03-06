package me.jbusdriver.common

import io.reactivex.subscribers.DisposableSubscriber
import org.reactivestreams.Subscription
import retrofit2.HttpException

/**
 * Created by Administrator on 2016/7/21 0021.
 */
open class SimpleSubscriber<T> : DisposableSubscriber<T>() {

    private val TAG: String = this.javaClass.name
    private  var sub: Subscription? = null

    override fun onStart() {
        super.onStart()
        KLog.t(TAG).i(": onStart >>")
    }

    override fun onComplete() {
        KLog.t(TAG).i("onCompleted >> ")
        sub?.cancel()
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        KLog.t(TAG).e("onError >> code = info : ${e.message}")
        if (e is HttpException){
            when (e.code()){
                404 -> AppContext.instace.toast("没有结果")
            }
        }
    }

    override fun onNext(t: T) {
        KLog.t(TAG).i("t = $t")
    }
}
