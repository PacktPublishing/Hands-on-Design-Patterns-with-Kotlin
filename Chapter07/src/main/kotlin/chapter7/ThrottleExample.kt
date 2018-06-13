package chapter7

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {

    throttleFirst()
    throttleLast()

}

private fun throttleLast() {
    println("Last")
    val o = Observable.intervalRange(8L, 15L, 5L, 100L, TimeUnit.MILLISECONDS)

    o.throttleLast(280L, TimeUnit.MILLISECONDS).subscribe {
        println(it)
    }

    o.buffer(280L,  TimeUnit.MILLISECONDS).subscribe {
        println(it)
    }

    Thread.sleep(100 * 30)
}

private fun throttleFirst() {
    val o = PublishSubject.intervalRange(8L, 15L, 0L, 100L, TimeUnit.MILLISECONDS).publish()

    o.throttleFirst(280L, TimeUnit.MILLISECONDS).subscribe {
        println(it)
    }

    o.buffer(280L,  TimeUnit.MILLISECONDS).subscribe {
        println(it)
    }

    o.connect()

    Thread.sleep(100 * 15)
}
