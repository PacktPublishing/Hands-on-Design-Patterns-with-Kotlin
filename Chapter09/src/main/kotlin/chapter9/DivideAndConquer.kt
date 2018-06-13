package chapter9

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.yield
import java.util.*
import kotlin.system.measureTimeMillis

fun main(vararg args: String) = runBlocking{


    val numbers = List(10_000_000) {
        Random().nextInt()
    }

    var millis = measureTimeMillis {
        println(numbers.max())
    }
    println("Took ${millis}ms")
    val input = Channel<List<Int>>()
    val output = collector()
    val dividers = List(10) {
        divide(input, output)
    }

    val start = System.currentTimeMillis()
    launch {
        for (c in numbers.chunked(1000)) {
            input.send(c)
        }
        input.close()
    }

    dividers.forEach {
        it.await()
    }
    output.close()

    println("Took ${System.currentTimeMillis() - start}ms")

}

fun divide(input: ReceiveChannel<List<Int>>,
           output: SendChannel<Int>) = async {
    var max = 0
    for (list in input) {
        for (i in list) {
            if (i > max) {
                max = i
                output.send(max)
            }
        }
    }
}

fun collector() = actor<Int> {
    var max = 0
    for (i in this) {
        max = Math.max(max, i)
    }
    println(max)
}