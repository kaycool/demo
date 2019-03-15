package kai.wang.demo.rxjava

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 * @author kai.w
 * @des  $des
 */
class RxBackPressedDemo {


    init {
//        normalWaitDownStream()
//        filterDownStream()
//        sampleDownStream()
//        sleepDownStream()
//        flowableUpStream()
        asyncFlowable()
    }


    @SuppressLint("CheckResult")
    fun normalWaitDownStream() {
        Observable.create<Int> {
            for (i in 0..Int.MAX_VALUE) {
                it.onNext(i)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Thread.sleep(2000)
                Log.d(RxCreateDemo.TAG, "onNext$it")
            }
    }

    @SuppressLint("CheckResult")
    fun filterDownStream() {
        Observable.create<Int> {
            for (i in 0..Int.MAX_VALUE) {
                it.onNext(i)
            }
        }.subscribeOn(Schedulers.io())
            .filter {
                it % 10 == 0
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Thread.sleep(2000)
                Log.d(RxCreateDemo.TAG, "onNext$it")
            }
    }

    @SuppressLint("CheckResult")
    fun sampleDownStream() {
        Observable.create<Int> {
            for (i in 0..Int.MAX_VALUE) {
                it.onNext(i)
            }
        }.subscribeOn(Schedulers.io())
            .sample(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Thread.sleep(2000)
                Log.d(RxCreateDemo.TAG, "onNext$it")
            }
    }


    @SuppressLint("CheckResult")
    fun sleepDownStream() {
        Observable.create<Int> {
            for (i in 0..Int.MAX_VALUE) {
                it.onNext(i)
                Thread.sleep(2000)
            }
        }.subscribeOn(Schedulers.io())
            .sample(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Thread.sleep(2000)
                Log.d(RxCreateDemo.TAG, "onNext$it")
            }
    }


    @SuppressLint("CheckResult")
    fun flowableUpStream(){
        val upstream = Flowable.create(FlowableOnSubscribe<Int> { emitter ->
            Log.d(RxCreateDemo.TAG, "emit 1")
            emitter.onNext(1)
            Log.d(RxCreateDemo.TAG, "emit 2")
            emitter.onNext(2)
            Log.d(RxCreateDemo.TAG, "emit 3")
            emitter.onNext(3)
            Log.d(RxCreateDemo.TAG, "emit complete")
            emitter.onComplete()
        }, BackpressureStrategy.ERROR) //增加了一个参数


        val downstream = object : FlowableSubscriber<Int> {

            override fun onComplete() {
                Log.d(RxCreateDemo.TAG, "onComplete")
            }

            override fun onSubscribe(s: org.reactivestreams.Subscription) {
                Log.d(RxCreateDemo.TAG, "onSubscribe")
//                s.request(java.lang.Long.MAX_VALUE)  //注意这句代码，下游处理事件的能力
            }

            override fun onNext(t: Int?) {
                Log.d(RxCreateDemo.TAG, "onNext$t")
            }

            override fun onError(t: Throwable?) {
                Log.w(RxCreateDemo.TAG, "onError: ", t)
            }
        }

        upstream.subscribe(downstream)

    }

    fun asyncFlowable(){
        // 上游会有128k的水缸存储未接收的事件
        Flowable.create(FlowableOnSubscribe<Int> { emitter ->
            for (i in 0..128) {
                Log.d(RxCreateDemo.TAG, "emit $i")
                emitter.onNext(i)
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : FlowableSubscriber<Int> {

                override fun onComplete() {
                    Log.d(RxCreateDemo.TAG, "onComplete")
                }

                override fun onSubscribe(s: org.reactivestreams.Subscription) {
                    Log.d(RxCreateDemo.TAG, "onSubscribe")
//                    s.request(java.lang.Long.MAX_VALUE)  //注意这句代码，下游处理事件的能力
                }

                override fun onNext(t: Int?) {
                    Log.d(RxCreateDemo.TAG, "onNext$t")
                }

                override fun onError(t: Throwable?) {
                    Log.w(RxCreateDemo.TAG, "onError: ", t)
                }
            })
    }


}