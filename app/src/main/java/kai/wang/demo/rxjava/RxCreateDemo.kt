package kai.wang.demo.rxjava

import android.util.Log
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription

/**
 * @author kai.w
 * @des  $des
 */
class RxCreateDemo {

    init {
        createObservable1()
//        createObservable2()
//        createObservable3()
//        createObservable4()
//        createObservable5()
    }


    private fun createObservable1() {
        Observable.create<Int> {
            it.onNext(1)
            it.onNext(2)
            it.onNext(3)
            it.onComplete()
            it.onNext(4)
            it.onNext(5)
        }.subscribe(object : Observer<Int> {
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                d.dispose()
            }

            override fun onNext(t: Int) {
                Log.d(TAG, "onNext$t")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError${e.message.toString()}")
            }
        })
    }

    /**
     * Maybe/MaybeObserver 可发射一条单一的数据，以及发射一条完成通知，其中完成通知和异常通知只能发射一个
     * ，发射数据只能在发射完成通知或者异常通知之前，否则发射数据无效 X->error 错误说法
     * Maybe/MaybeObserver 只发射一条单一的数据 或者一条完成通知或者一个异常通知
     */
    private fun createObservable2() {
//        Maybe.just(isLogin)
//            .subscribe(object : MaybeObserver<Boolean> {
//                override fun onSuccess(t: Boolean) {
//                }
//
//                override fun onComplete() {
//                }
//
//                override fun onSubscribe(d: Disposable) {
//                }
//
//                override fun onError(e: Throwable) {
//                }
//            })

        Maybe.create<Int> {
            it.onSuccess(1)
            it.onSuccess(2)
            it.onComplete()
            it.onError(Throwable("Maybe onError"))
        }.subscribe(object : MaybeObserver<Int> {
            override fun onSuccess(t: Int) {
                Log.d(TAG, "onSuccess$t")
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, e.message.toString())
            }

        })
    }


    /**
     * Single/SingleObserver 只发射一条数据通知或一条异常通知，不能发射完成通知
     */
    private fun createObservable3() {
        Single.create<Int> {
            it.onSuccess(1)
//            it.onError(Throwable("Single onError"))
        }.subscribe(object : SingleObserver<Int> {

            override fun onSubscribe(d: Disposable) {

            }

            override fun onSuccess(t: Int) {

            }

            override fun onError(e: Throwable) {
            }
        })
    }

    /**
     * Completable/CompletableObserver 只发射一条完成通知或异常通知，不能发射数据通知
     */
    private fun createObservable4() {
        Completable.create {
            it.onComplete()
//            it.onError(Throwable("Completable error"))
        }.subscribe(object : CompletableObserver {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {

            }

        })
    }

    /**
     * Flowable 支持背压式
     */
    private fun createObservable5() {
        Flowable.create<Int>({
            for (i in 0 until 129) {
                it.onNext(i)
            }
        }, BackpressureStrategy.ERROR)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : FlowableSubscriber<Int> {
                override fun onComplete() {

                }

                override fun onSubscribe(s: Subscription) {
//                    s.request(10)
                }

                override fun onNext(t: Int?) {
                    Log.d(TAG, "onNext$t")
                }

                override fun onError(t: Throwable?) {
                    Log.d(TAG, "onError${t?.message.toString()}")
                }

            })
    }

    companion object {
        val TAG = "Rxjava2.0"
    }
}