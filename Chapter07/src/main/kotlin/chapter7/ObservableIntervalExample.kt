package chapter7

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {

    val publisher = Observable.interval(1, TimeUnit.SECONDS)

    publisher.subscribe {
        println("P1 $it")
    }

    val subscription = publisher.subscribe {
        println("P2 $it")
    }

    println("Sleeping")
    Thread.sleep(TimeUnit.SECONDS.toMillis(2))
    subscription.dispose()

    println("Sleeping")
    Thread.sleep(TimeUnit.SECONDS.toMillis(3))
}
