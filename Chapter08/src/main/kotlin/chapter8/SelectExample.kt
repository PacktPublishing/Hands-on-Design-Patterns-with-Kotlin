package chapter8

import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.selects.select
import java.util.*

fun main(args: Array<String>) = runBlocking {


    val firstProducer = produce {
        delay(Random().nextInt(100))
        send("First")
        println("firstProducer done")
    }

    val secondProducer = produce {
        try {
            delay(Random().nextInt(100))
            send("Second")
            println("secondProducer done")
        }
        catch (e: Exception) {
            e.printStackTrace()
        }

    }

    val winner = select<String> {
        firstProducer.onReceive {
            it.toLowerCase()
        }
        secondProducer.onReceive {
            it.toUpperCase()
        }
    }

    println(winner)

    delay(5000)
}