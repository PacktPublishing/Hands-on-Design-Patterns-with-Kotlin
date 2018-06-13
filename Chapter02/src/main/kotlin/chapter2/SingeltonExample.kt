package chapter2

import java.util.concurrent.atomic.AtomicInteger

object CounterSingleton {

    init {
        println("I was accessed for the first time")
    }

    private val counter = AtomicInteger(0)
    fun increment() = counter.incrementAndGet()
}

fun main(args : Array<String>) {

    for (i in 1..10) {
        println(CounterSingleton.increment())
    }


}
