package kai.wang.demo.rxjava

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author kai.w
 * @des  $des
 */
class RxSchedulerDemo {

    /**
     * Schedulers.io()->代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
     * Schedulers.computation()->代表CPU计算密集型的操作, 例如需要大量计算的操作
     * Schedulers.newThread()->代表一个常规的新线程
     * AndroidSchedulers.mainThread()->代表Android的主线程
     */


    init {
        schedulerSameThread()
//        schedulerIoToMain()
    }

    private fun schedulerSameThread() {
        Observable.create<Int> {
            Log.d(RxCreateDemo.TAG, "Observable thread=${Thread.currentThread().name}")
            it.onNext(1)
        }.subscribe(object : Observer<Int> {
            override fun onComplete() {
                Log.d(RxCreateDemo.TAG, "Observer thread=${Thread.currentThread().name}")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Int) {
                Log.d(RxCreateDemo.TAG, "Observer thread=${Thread.currentThread().name}")
            }

            override fun onError(e: Throwable) {
                Log.d(RxCreateDemo.TAG, "Observer thread=${Thread.currentThread().name}")
            }
        })
    }

    private fun schedulerIoToMain() {
        Observable.create<Int> {
            Log.d(RxCreateDemo.TAG, "Observable thread=${Thread.currentThread().name}")
            it.onNext(1)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    Log.d(RxCreateDemo.TAG, "Observer thread=${Thread.currentThread().name}")
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    Log.d(RxCreateDemo.TAG, "Observer thread=${Thread.currentThread().name}")
                }

                override fun onError(e: Throwable) {
                    Log.d(RxCreateDemo.TAG, "Observer thread=${Thread.currentThread().name}")
                }
            })
    }

}