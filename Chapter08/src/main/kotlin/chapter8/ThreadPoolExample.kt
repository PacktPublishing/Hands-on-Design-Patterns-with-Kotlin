package chapter8

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

fun main(args: Array<String>) {
    // Try setting this to 1, number of cores, 100, 2000, 3000 and see what happens
    val pool = Executors.newFixedThreadPool(100)

    val counter = AtomicInteger(0)

    val start = System.currentTimeMillis()
    for (i in 1..10_000) {
        var f = pool.submit {
            // Do something
            counter.incrementAndGet()

            // Simulate wait on IO
            Thread.sleep(100)

            // Do something again
            counter.incrementAndGet()
        }
    }

    pool.awaitTermination(20, TimeUnit.SECONDS)
    pool.shutdown()

    println("Took me ${System.currentTimeMillis() - start} millis to complete ${counter.get() / 2} tasks")
}