package chapter7

import io.reactivex.Observable
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {


    batchBySize()
    batchByTime()
    batchByObservable()

}

fun batchByObservable() {
    println("By Observable")
    val latch = CountDownLatch(1)
    val o = Observable.intervalRange(8L, 15L, 0L, 100L, TimeUnit.MILLISECONDS)

    o.buffer(Observable.interval(200L, TimeUnit.MILLISECONDS)).subscribe ({
        println(it)
    }, {}, { latch.countDown() })

    latch.await()
}

fun batchBySize() {
    println("By size")
    val latch = CountDownLatch(1)
    val o = Observable.intervalRange(8L, 15L, 0L, 100L, TimeUnit.MILLISECONDS)

    o.buffer(3).subscribe({
        println(it)
    }, {}, { latch.countDown()})

    latch.await()
}

fun batchByTime() {
    println("By time")
    val latch = CountDownLatch(1)
    val o = Observable.intervalRange(8L, 15L, 0L, 100L, TimeUnit.MILLISECONDS)

    o.buffer(300L, TimeUnit.MILLISECONDS).subscribe ({
        println(it)
    }, {}, { latch.countDown() })

    latch.await()
}
