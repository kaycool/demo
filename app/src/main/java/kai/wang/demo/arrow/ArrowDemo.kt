//package kai.wang.demo.arrow
//
//import arrow.effects.Ref.Companion.unsafe
//import kotlinx.coroutines.ObsoleteCoroutinesApi
//import kotlinx.coroutines.newSingleThreadContext
//import kotlinx.coroutines.runBlocking
//import reactor.core.scheduler.NonBlocking
//
///**
// * @author kai.w
// * @des  $des
// */
//class ArrowDemo{
//     fun helloWorld() = "hello world"
//
//    suspend fun sayHello() = println(helloWorld())
//
//    @ObsoleteCoroutinesApi
//    val contextA = newSingleThreadContext("A")
//
//    suspend fun printThreadName(): Unit =
//        println(Thread.currentThread().name)
//
//    val program = fx {
//        !effect { printThreadName() }
//    }
//
//    fun main() { // The edge of our world
//        unsafe { runBlocking { program } }
//    }
//
//
//    init {
////        sayHello()
//        main()
//    }
//}
//
