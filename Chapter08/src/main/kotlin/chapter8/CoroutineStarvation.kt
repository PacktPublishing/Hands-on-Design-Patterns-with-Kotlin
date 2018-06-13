package me.soshin.b

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


/**
 * Behind coroutines there's still an event loop
 * Tight CPU bound loops in one coroutine will starve other coroutines
 */
fun main(args: Array<String>) {

    val latch = CountDownLatch(10 * 2)
    for (i in 1..10) {
        async(CommonPool) {
            AbstractGenericCoroutineFactory.greedyLongCoroutine(i)
            latch.countDown()
        }
    }

    for (i in 1..10) {
        async(CommonPool) {
            AbstractGenericCoroutineFactory.shortCoroutine(i)
            latch.countDown()
        }
    }

    latch.await(10, TimeUnit.SECONDS)
}

object AbstractGenericCoroutineFactory {
    fun greedyLongCoroutine(index: Int) {

        var uuid = UUID.randomUUID()
        for (i in 1..100_000) {
            val newUuid = UUID.randomUUID()

            if (newUuid < uuid) {
                uuid = newUuid
            }
        }

        println("Done greedyLongCoroutine $index")
    }

    fun shortCoroutine(index: Int) {
        println("Done shortCoroutine $index!")
    }
}