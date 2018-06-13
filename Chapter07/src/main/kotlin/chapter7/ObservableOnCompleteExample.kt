package chapter7

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {

    //val o = Observable.intervalRange(5, 100, 0L, 1L, TimeUnit.SECONDS)

    val o = Observable.create<Int> {
        for (i in 1..10_000) {
            it.onNext(i)
        }
        it.onComplete()
    }

    o.subscribe {
        println("P1 $it")
    }


    o.subscribe {
        println("P2 $it")
    }

    println("S2")
    Thread.sleep(TimeUnit.SECONDS.toMillis(3))
}