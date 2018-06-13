package me.soshin.c

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.yield
import me.soshin.b.AbstractGenericCoroutineFactory
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * We're creating extension function that will yield once in a while
 * yield lets other coroutines work in the meantime
 */
suspend fun AbstractGenericCoroutineFactory.longCoroutine(index: Int) {
    var uuid = UUID.randomUUID()
    for (i in 1..100_000) {
        val newUuid = UUID.randomUUID()

        if (newUuid < uuid) {
            uuid = newUuid
        }

        if (i % 100 == 0) {
            yield()
        }
    }

    println("Done longCoroutine $index")
}

/**
 * Coroutine cannot stop at any point of code, but only at suspending point
 * If you have tight loops, use yield once in a while
 */
fun main(args: Array<String>) {

    val latch = CountDownLatch(10 * 2)
    for (i in 1..10) {
        launch(CommonPool) {
            AbstractGenericCoroutineFactory.longCoroutine(i)
            latch.countDown()
        }
    }

    // Same as above loop, but neater
    List(10) { i ->
        launch(CommonPool) {
            AbstractGenericCoroutineFactory.shortCoroutine(i)
            latch.countDown()
        }
    }

    latch.await(10, TimeUnit.SECONDS)
}