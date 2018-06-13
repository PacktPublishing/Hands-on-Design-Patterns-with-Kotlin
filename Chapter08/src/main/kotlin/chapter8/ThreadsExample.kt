package chapter8

import kotlin.concurrent.thread

fun main(args: Array<String>) {

    val t1 = thread {
        for (i in 1..100) {
            println("T1: $i")
        }
    }

    val t2 = thread(start = false) {
        for (i in 1..100) {
            println("T2: $i")
        }
    }

    t2.start()

    val t3 = thread(isDaemon = true) {
        for (i in 1..1_000_000) {
            println("T3: $i")
        }
    }
}