package kai.wang.demo.rxjava

import android.util.Log
import io.reactivex.CompletableObserver
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import rx.exceptions.Exceptions
import java.util.concurrent.TimeUnit

/**
 * @author kai.w
 * @des  $des
 */
class RxOptionDemo {

    init {
//        justOption()
//        fromArrayOption()
//        emptyOption()
//        errorOption()
//        neverOption()
//        timerOption()
//        intervalOption()
//        rangeOption()
//        deferOption()

//        mergeOption()
//        zipOption()
        combineLatestOption()
    }


    /**
     * just option
     */
    private fun justOption() {
        Observable.just("hello", "Bob", "Test").subscribe(object : Observer<String> {
            override fun onComplete() {
                Log.d(RxCreateDemo.TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                Log.d(RxCreateDemo.TAG, "onNext$t")
            }

            override fun onError(e: Throwable) {
                Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
            }

        })
    }

    /**
     * from option
     */
    private fun fromArrayOption() {
        Observable.fromArray(arrayOf("hello", "Bob", "Test")).subscribe(object : Observer<Array<String>> {
            override fun onComplete() {
                Log.d(RxCreateDemo.TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Array<String>) {
                Log.d(RxCreateDemo.TAG, "onNext$t")
            }

            override fun onError(e: Throwable) {
                Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
            }

        })
    }

    /**
     * fromIterable 接收一个list集合
     */
    private fun fromIterableOption() {
        Observable.fromIterable(mutableListOf("i", "am", "list"))
            .subscribe(object : Observer<String> {
                override fun onComplete() {
                    Log.d(RxCreateDemo.TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    Log.d(RxCreateDemo.TAG, "onNext$t")
                }

                override fun onError(e: Throwable) {
                    Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
                }

            })
    }

    /**
     * empty option 创建一个什么都不做直接通知完成的Observable
     */
    private fun emptyOption() {
        Observable.empty<Int>().subscribe(object : Observer<Int> {
            override fun onComplete() {
                Log.d(RxCreateDemo.TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Int) {
                Log.d(RxCreateDemo.TAG, "onNext$t")
            }

            override fun onError(e: Throwable) {
                Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
            }

        })
    }

    /**
     * error option 创建一个什么都不做直接通知错误的Observable
     */
    private fun errorOption() {
        Observable.error<Int>(Throwable("error")).subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                Log.d(RxCreateDemo.TAG, "onComplete")
            }

            override fun onNext(t: Int) {
                Log.d(RxCreateDemo.TAG, "onNext$t")
            }

            override fun onError(e: Throwable) {
                Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
            }

            override fun onComplete() {

            }

        })
    }

    /**
     * never option 创建一个什么都不做的Observable
     */
    private fun neverOption() {
        Observable.never<Int>().subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onComplete() {
                Log.d(RxCreateDemo.TAG, "onComplete")
            }

            override fun onNext(t: Int) {
                Log.d(RxCreateDemo.TAG, "onNext$t")
            }

            override fun onError(e: Throwable) {
                Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
            }

        })
    }

    /**
     * timer option 创建一个在给定的延时之后
     */
    private fun timerOption() {
        Observable.timer(1000, TimeUnit.SECONDS).subscribe(object : Observer<Long> {
            override fun onComplete() {
                Log.d(RxCreateDemo.TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Long) {
                Log.d(RxCreateDemo.TAG, "onNext$t")
            }

            override fun onError(e: Throwable) {
                Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
            }

        })
    }

    /**
     * interval option 创建一个按照给定的时间间隔发射从0开始的整数序列的
     */
    private fun intervalOption() {
        Observable.interval(1, TimeUnit.SECONDS).subscribe(object : Observer<Long> {
            override fun onComplete() {
                Log.d(RxCreateDemo.TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Long) {
                //每隔1秒发送数据项，从0开始计数
                //0,1,2,3....
                Log.d(RxCreateDemo.TAG, "onNext$t")
            }

            override fun onError(e: Throwable) {
                Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
            }

        })
    }

    /**
     * range option 创建一个发射指定范围的整数序列的Observable<Integer>
     */
    private fun rangeOption() {
        Observable.range(2, 5).subscribe(object : Observer<Int> {
            override fun onComplete() {
                Log.d(RxCreateDemo.TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Int) {
                // 2,3,4,5,6 从2开始发射5个数据
                Log.d(RxCreateDemo.TAG, "onNext$t")
            }

            override fun onError(e: Throwable) {
                Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
            }

        })
    }


    /**
     * defer option 只有当订阅者订阅才创建Observable，为每个订阅创建一个新的Observable。
     * 内部通过OnSubscribeDefer在订阅时调用Func0创建Observable。
     */
    private fun deferOption() {
        Observable.defer<Int> {
            Observable.just(1)
        }.subscribe(object : Observer<Int> {
            override fun onComplete() {
                Log.d(RxCreateDemo.TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Int) {
                Log.d(RxCreateDemo.TAG, "onNext$t")
            }

            override fun onError(e: Throwable) {
                Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
            }

        })
    }


    /**
     * concat 按顺序连接多个Observables。
     * 需要注意的是Observable.concat(a,b)等价于a.concatWith(b)。
     */
    private fun concatOption() {
        val a = Observable.just(1, 2, 3, 4)
        val b = Observable.just(5, 6)
        Observable.concat(a, b).subscribe(object : Observer<Int> {
            override fun onComplete() {
                Log.d(RxCreateDemo.TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Int) {
                Log.d(RxCreateDemo.TAG, "onNext$t")
            }

            override fun onError(e: Throwable) {
                Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
            }

        })
    }


    /**
     * concatWith == concat
     */
    private fun concatWithOption() {
        val a = Observable.just(1, 2, 3, 4)
        val b = Observable.just(5, 6)
        a.concatWith(b).subscribe(object : Observer<Int> {
            override fun onComplete() {
                Log.d(RxCreateDemo.TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Int) {
                Log.d(RxCreateDemo.TAG, "onNext$t")
            }

            override fun onError(e: Throwable) {
                Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
            }

        })
    }

    /**
     * startWith 在数据序列的开头增加一项数据。startWith的内部也是调用了concat
     */
    private fun startWithOption() {
        Observable.just(3, 4, 5)
            .startWith(mutableListOf(1, 2))
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    Log.d(RxCreateDemo.TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    Log.d(RxCreateDemo.TAG, "onNext$t")
                }

                override fun onError(e: Throwable) {
                    Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
                }

            })
    }


    /**
     * merge  按照时间线将多个Observable合并为一个
     * 遇到异常将停止发射数据，发送onError通知
     */
    private fun mergeOption() {
        //1 2 3 error 4
        val a = Observable.just(1).delay(0, TimeUnit.SECONDS)
        val b = Observable.just(2).delay(1000, TimeUnit.SECONDS)
        val c = Observable.just(4).delay(3000, TimeUnit.SECONDS)
        val d = Observable.just(3).delay(2000, TimeUnit.SECONDS)
//        val e = Observable.error<Int>(Throwable("2500 second error")).delay(2500, TimeUnit.SECONDS)
        Observable.merge(a, b, c, d).subscribe(object : Observer<Int> {
            override fun onComplete() {
                Log.d(RxCreateDemo.TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Int) {
                Log.d(RxCreateDemo.TAG, "onNext$t")
            }

            override fun onError(e: Throwable) {
                Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
            }
        })
    }

    /**
     * mergeDelayError  按照时间线将多个Observable合并为一个
     * 将异常延迟到其它没有错误的Observable发送完毕后才发射
     */
    private fun mergeDelayErrorOption() {
        val a = Observable.just(1).delay(0, TimeUnit.SECONDS)
        val b = Observable.just(2).delay(1000, TimeUnit.SECONDS)
        val c = Observable.just(4).delay(3000, TimeUnit.SECONDS)
        val d = Observable.just(3).delay(2000, TimeUnit.SECONDS)
        val e = Observable.error<Any>(Throwable("2500 second error")).delay(2500, TimeUnit.SECONDS)
        Observable.mergeDelayError(mutableListOf(a, b, c, d, e)).subscribe(object : Observer<Any> {
            override fun onComplete() {
                Log.d(RxCreateDemo.TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Any) {
                Log.d(RxCreateDemo.TAG, "onNext$t")
            }

            override fun onError(e: Throwable) {
                Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
            }
        })
    }

    /**
     * zip option 使用一个函数组合多个Observable发射的数据集合，然后再发射这个结果
     */
    private fun zipOption() {
        val a = Observable.just(1, 2, 3, 4)
        val b = Observable.just("a", "b", "c")
        Observable.zip(a, b, BiFunction<Int, String, String> { t1, t2 -> "$t1 and $t2 " })
            .subscribe(object : Observer<String> {
                override fun onComplete() {
                    Log.d(RxCreateDemo.TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: String) {
                    Log.d(RxCreateDemo.TAG, "onNext$t")
                }

                override fun onError(e: Throwable) {
                    Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
                }

            })
    }


    /**
     * combineLatest 当两个Observables中的任何一个发射了一个数据时
     * ，通过一个指定的函数组合每个Observable发射的最新数据（一共两个数据）
     * ，然后发射这个函数的结果。类似于zip，
     * 但是，不同的是zip只有在每个Observable都发射了数据才工作
     * ，而combineLatest任何一个发射了数据都可以工作
     * ，每次与另一个Observable最近的数据压合
     */
    private fun combineLatestOption() {
        val a = Observable.just(1, 2, 3, 4)
        val b = Observable.just("a", "b", "c")
        Observable.combineLatest(b, a, BiFunction<String, Int, String> { t1, t2 -> "$t1 and $t2 " })
            .subscribe(object : Observer<String> {
                override fun onComplete() {
                    Log.d(RxCreateDemo.TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: String) {
                    Log.d(RxCreateDemo.TAG, "onNext$t")
                }

                override fun onError(e: Throwable) {
                    Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
                }

            })
    }

    /**
     * filter 过滤数据
     */
    private fun filterOption() {
        Observable.just(1, 2, 3, 4)
            .filter { it > 2 }
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    Log.d(RxCreateDemo.TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Int) {
                    Log.d(RxCreateDemo.TAG, "onNext$t")
                }

                override fun onError(e: Throwable) {
                    Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
                }

            })
    }

    /**
     * ofType option :过滤指定类型的数据，与filter类似，
     */
    private fun ofTypeOption() {
        Observable.just("1", 2, "3")
            .ofType(Int::class.java)
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    Log.d(RxCreateDemo.TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Int) {
                    Log.d(RxCreateDemo.TAG, "onNext$t")
                }

                override fun onError(e: Throwable) {
                    Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
                }

            })
    }

    /**
     * take option : 只发射开始的N项数据或者一定时间内的数据
     */
    private fun takeOption() {
        Observable.just(1, 2, 3, 4, 5)
            .take(3)//发射前三个数据
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    Log.d(RxCreateDemo.TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Int) {
                    Log.d(RxCreateDemo.TAG, "onNext$t")
                }

                override fun onError(e: Throwable) {
                    Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
                }

            })
    }

    private fun takeOption2() {
        val a = Observable.just(1).delay(100, TimeUnit.SECONDS)
        val b = Observable.just(2).delay(200, TimeUnit.SECONDS)
        val c = Observable.just(3).delay(300, TimeUnit.SECONDS)
        val d = Observable.just(4).delay(400, TimeUnit.SECONDS)

        Observable.concat(a, b, c, d)
            .take(200, TimeUnit.SECONDS)//最后200ms的数据->3,4
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    Log.d(RxCreateDemo.TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Int) {
                    Log.d(RxCreateDemo.TAG, "onNext$t")
                }

                override fun onError(e: Throwable) {
                    Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
                }

            })
    }

    /**
     * takeLast 只发射最后的N项数据或者一定时间内的数据
     */
    private fun takeLastOption() {
        Observable.just(1, 2, 3, 4)
            .takeLast(2)//最后两项数据3,4
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    Log.d(RxCreateDemo.TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Int) {
                    Log.d(RxCreateDemo.TAG, "onNext$t")
                }

                override fun onError(e: Throwable) {
                    Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
                }

            })
    }

    private fun takeLastOption2() {
        val a = Observable.just(1).delay(100, TimeUnit.SECONDS)
        val b = Observable.just(2).delay(200, TimeUnit.SECONDS)
        val c = Observable.just(3).delay(300, TimeUnit.SECONDS)
        val d = Observable.just(4).delay(400, TimeUnit.SECONDS)

        Observable.concat(a, b, c, d)
            .takeLast(200, TimeUnit.SECONDS)//最后200ms的数据->3,4
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    Log.d(RxCreateDemo.TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Int) {
                    Log.d(RxCreateDemo.TAG, "onNext$t")
                }

                override fun onError(e: Throwable) {
                    Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
                }

            })
    }

    /**
     * first option:只发射第一项（或者满足某个条件的第一项）数据，可以指定默认值
     */
    private fun firstOption() {
        Observable.just(1, 2, 3, 4)
            .first(3)
            .subscribe(object : SingleObserver<Int> {
                override fun onSuccess(t: Int) {
                    Log.d(RxCreateDemo.TAG, "onSuccess$t")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
                }

            })
    }

    /**
     * last option:只发射第一项（或者满足某个条件的第一项）数据，可以指定默认值
     */
    private fun lastOption() {
        Observable.just(1, 2, 3, 4)
            .last(3)
            .subscribe(object : SingleObserver<Int> {
                override fun onSuccess(t: Int) {
                    Log.d(RxCreateDemo.TAG, "onSuccess$t")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
                }

            })
    }

    /**
     *  skip/skipLast option:跳过开始的N项数据或者一定时间内的数据
     */
    private fun skipOption1() {
        Observable.just(1, 2, 3, 4)
            .skip(1)
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    Log.d(RxCreateDemo.TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Int) {
                    Log.d(RxCreateDemo.TAG, "onNext$t")
                }

                override fun onError(e: Throwable) {
                    Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
                }

            })
    }

    private fun skipOption2() {
        val a = Observable.just(1).delay(100, TimeUnit.SECONDS)
        val b = Observable.just(2).delay(200, TimeUnit.SECONDS)
        val c = Observable.just(3).delay(300, TimeUnit.SECONDS)
        val d = Observable.just(4).delay(400, TimeUnit.SECONDS)

        Observable.concat(a, b, c, d)
            .skip(200, TimeUnit.SECONDS)
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    Log.d(RxCreateDemo.TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Int) {
                    Log.d(RxCreateDemo.TAG, "onNext$t")
                }

                override fun onError(e: Throwable) {
                    Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
                }

            })
    }

    /**
     * ignoreElements 丢弃所有数据，只发射错误或正常终止的通知。内部通过OperatorIgnoreElements实现。
     */
    private fun ignoreElementsOption() {
        Observable.just(1, 2, 3, 4)
            .ignoreElements()
            .subscribe(object : CompletableObserver {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

            })
    }


    /**
     * distinct option:过滤重复数据
     * distinctUntilChanged option:过滤掉连续重复的数据
     */
    private fun distinctOption() {
        Observable.just(1, 2, 3, 4, 2, 3, 5)
            .distinct()
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    Log.d(RxCreateDemo.TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Int) {
                    Log.d(RxCreateDemo.TAG, "onNext$t")
                }

                override fun onError(e: Throwable) {
                    Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
                }

            })
    }

    fun sampleOption(){
        Observable.create<Any> { subscriber ->
            subscriber.onNext(1)
            try {
                Thread.sleep(500)
            } catch (e: InterruptedException) {
                throw Exceptions.propagate(e)
            }

            subscriber.onNext(2)
            try {
                Thread.sleep(500)
            } catch (e: InterruptedException) {
                throw Exceptions.propagate(e)
            }

            subscriber.onNext(3)
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                throw Exceptions.propagate(e)
            }

            subscriber.onNext(4)
            subscriber.onNext(5)
            subscriber.onComplete()

        }.sample(999, TimeUnit.MILLISECONDS)//或者为throttleLast(1000, TimeUnit.MILLISECONDS)
            .subscribe { item -> Log.d("JG", item.toString()) } //结果为2,3,5

    }

    fun connectObservable() {
        val observable = Observable.just(1, 2, 3, 4, 2, 3, 5).publish()
        observable.subscribe(object : Observer<Int> {
            override fun onComplete() {
                Log.d(RxCreateDemo.TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Int) {
                Log.d(RxCreateDemo.TAG, "onNext$t")
            }

            override fun onError(e: Throwable) {
                Log.d(RxCreateDemo.TAG, "onError ${e.message.toString()}")
            }

        })

        observable.connect()
    }

    /**
     * throttleFirst option:定期发射Observable发射的第一项数据
     * throttleWithTimeout/debounce:发射数据时，如果两次数据的发射间隔小于指定时间，
     * 就会丢弃前一次的数据,直到指定时间内都没有新数据发射时才进行发射
     * sample/throttleLast:定期发射Observable最近的数据
     * timeout:如果原始Observable过了指定的一段时长没有发射任何数据，就发射一个异常或者使用备用的Observable
     * all：判断所有的数据项是否满足某个条件
     * exists: 判断是否存在数据项满足某个条件
     * contains: 判断在发射的所有数据项中是否包含指定的数据
     * sequenceEqual: 用于判断两个Observable发射的数据是否相同
     * isEmpty:用于判断Observable发射完毕时，有没有发射数据。有数据false，如果只收到了onComplete通知则为true
     * amb: 给定多个Observable，只让第一个发射数据的Observable发射全部数据，其他Observable将会被忽略
     * switchIfEmpty: 如果原始Observable正常终止后仍然没有发射任何数据，就使用备用的Observable
     * defaultIfEmpty: 如果原始Observable正常终止后仍然没有发射任何数据，就发射一个默认值,内部调用的switchIfEmpty
     * takeUntil: 当发射的数据满足某个条件后（包含该数据），或者第二个Observable发送完毕，终止第一个Observable发送数据。
     * takeWhile: 当发射的数据满足某个条件时（不包含该数据），Observable终止发送数据
     * skipUntil: 丢弃Observable发射的数据，直到第二个Observable发送数据。（丢弃条件数据）
     * skipWhile: 丢弃Observable发射的数据，直到一个指定的条件不成立（不丢弃条件数据）
     *
     * 聚合操作->
     * reduce: 对序列使用reduce()函数并发射最终的结果
     * collect: 使用collect收集数据到一个可变的数据结构。
     * count/countLong：计算发射的数量，内部调用的是reduce.
     * doOnNext(): 允许我们在每次输出一个元素之前做一些额外的事情。
     *
     * 转换操作->
     * toList：收集原始Observable发射的所有数据到一个列表，然后返回这个列表
     * toSortedList: 收集原始Observable发射的所有数据到一个有序列表，然后返回这个列表。
     * toMap: 将序列数据转换为一个Map。我们可以根据数据项生成key和生成value。
     * toMultiMap: 类似于toMap，不同的地方在于map的value是一个集合。
     *
     * 变换操作->
     * map: 对Observable发射的每一项数据都应用一个函数来变换
     * cast: 在发射之前强制将Observable发射的所有数据转换为指定类型
     * flatMap: 将Observable发射的数据变换为Observables集合
     * ，然后将这些Observable发射的数据平坦化的放进一个单独的Observable，内部采用merge合并。
     * flatMapIterable: 和flatMap的作用一样，只不过生成的是Iterable而不是Observable。
     * concatMap：类似于flatMap，由于内部使用concat合并，所以是按照顺序连接发射。
     * switchMap: 和flatMap很像，将Observable发射的数据变换为Observables集合，
     * 当原始Observable发射一个新的数据（Observable）时，它将取消订阅前一个Observable。
     * scan: 与reduce很像，对Observable发射的每一项数据应用一个函数，然后按顺序依次发射每一个值。
     * groupBy: 将Observable分拆为Observable集合，将原始Observable发射的数据按Key分组，每一个Observable发射一组不同的数据。
     * buffer: 它定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个
     * window: 定期将来自Observable的数据分拆成一些Observable窗口，然后发射这些窗口，而不是每次发射一项。
     *
     * 错误处理/重试机制->
     * onErrorResumeNext: 当原始Observable在遇到错误时，使用备用Observable
     * onExceptionResumeNext: 当原始Observable在遇到异常时，使用备用的Observable。与onErrorResumeNext类似
     * ，区别在于onErrorResumeNext可以处理所有的错误，onExceptionResumeNext只能处理异常。
     * onErrorReturn: 当原始Observable在遇到错误时发射一个特定的数据。
     * retry: 当原始Observable在遇到错误时进行重试。
     * retryWhen: 当原始Observable在遇到错误，将错误传递给另一个Observable来决定是否要重新订阅这个Observable,内部调用的是retry。
     *
     * 连接操作->
     * ConnectableObservable.connect(): 指示一个可连接的Observable开始发射数据.
     * Observable.publish(): 将一个Observable转换为一个可连接的Observable
     * Observable.replay(): 确保所有的订阅者看到相同的数据序列的ConnectableObservable，即使它们在Observable开始发射数据之后才订阅
     * ConnectableObservable.refCount(): 让一个可连接的Observable表现得像一个普通的Observable。
     *
     * 阻塞操作->
     * blockingForEach(): 对Observable发射的每一项数据调用一个方法，会阻塞直到Observable完成
     * blockingFirst(): 	阻塞直到Observable发射了一个数据，然后返回第一项数据
     * blockingMostRecent(): 返回一个总是返回Observable最近发射的数据的iterable
     * blockingLatest(): 返回一个iterable，会阻塞直到或者除非Observable发射了一个iterable没有返回的值，然后返回这个值
     * blockingNext(): 	返回一个iterable,阻塞直到返回另外一个值
     * blockingLast(): 阻塞直到Observable终止，然后返回最后一项数据
     * blockingIterable(): 将Observable转换返回一个iterable.
     * blockingSingle(): 如果Observable终止时只发射了一个值，返回那个值，否则抛出异常
     * blockingSubscribe(): 	在当前线程订阅，和forEach类似
     *
     * 工具集->
     * materialize: 将Observable转换成一个通知列表。
     * dematerialize: 与materialize的作用相反，将通知逆转回一个Observable。
     * timestamp: 给Observable发射的每个数据项添加一个时间戳。
     * timeInterval: 给Observable发射的两个数据项间添加一个时间差，实现在OperatorTimeInterval中timeInterval
     * serialize: 强制Observable按次序发射数据并且要求功能是完好的
     * cache: 缓存Observable发射的数据序列并发射相同的数据序列给后续的订阅者
     * observeOn: 指定观察者观察Observable的调度器
     * subscribeOn: 指定Observable执行任务的调度器
     * doOnEach: 注册一个动作，对Observable发射的每个数据项使用
     * doOnCompleted: 注册一个动作，对正常完成的Observable使用
     * doOnError: 注册一个动作，对发生错误的Observable使用
     * doOnTerminate: 注册一个动作，对完成的Observable使用，无论是否发生错误
     * doOnSubscribe: 注册一个动作，在观察者订阅时使用。内部由OperatorDoOnSubscribe实现doOnSubscribe
     * doOnUnsubscribe: 注册一个动作，在观察者取消订阅时使用。内部由OperatorDoOnUnsubscribe实现，在call中加入一个解绑动作。
     * finallyDo/doAfterTerminate：注册一个动作，在Observable完成时使用
     * delay: 延时发射Observable的结果
     * delaySubscription: 延时处理订阅请求。
     * using:  创建一个只在Observable生命周期存在的资源，当Observable终止时这个资源会被自动释放。
     * single/singleOrDefault：强制返回单个数据，否则抛出异常或默认数据。
     */

}