package chapter6

import kotlin.streams.toList
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {

    val numbers = (1..1_000_000).toList()

    println(measureTimeMillis {
        numbers.asSequence().map {
            it * it
        }
    })

    println(numbers.size)
    println(measureTimeMillis {
        numbers.map {
            it * it
        }
    })


}