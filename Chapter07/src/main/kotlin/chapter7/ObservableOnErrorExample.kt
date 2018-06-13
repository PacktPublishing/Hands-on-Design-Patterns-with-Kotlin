package chapter7

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import java.util.*
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {

    //val o = Observable.intervalRange(5, 100, 0L, 1L, TimeUnit.SECONDS)

    val o = Observable.create<Int> {
        it.onError(RuntimeException())
    }

    o.subscribe({
        println("All went good: $it")
    }, {
        println("There was an error $it")
    }, { println("Done") } )
    

    println("S2")
    Thread.sleep(TimeUnit.SECONDS.toMillis(3))
}