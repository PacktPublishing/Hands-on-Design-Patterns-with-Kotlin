package chapter8

import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

fun main(args: Array<String>) {

    val counter = AtomicInteger()
    try {
        for (i in 0..10_000) {
            thread {
                counter.incrementAndGet()
                Thread.sleep(100)
            }
        }
    } catch (oome: OutOfMemoryError) {
        println("Spawned ${counter.get()} threads before crashing")
        System.exit(-42)
    }
}