package chapter8

import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread

fun main(args: Array<String>) {

    //notSafe()
    synch()
}

fun synch() {
    var counter = 0
    val latch = CountDownLatch(100_000)
    for (i in 1..100_000) {
        thread{
            synchronized(latch) {
                counter++
                latch.countDown()
            }
        }
    }

    latch.await()
    println("Counter $counter")
}

fun notSafe() {
    var counter = 0
    val latch = CountDownLatch(100_000)
    for (i in 1..100_000) {
        thread{
            counter++
            latch.countDown()
        }
    }

    latch.await()
    println("Counter $counter")
}
