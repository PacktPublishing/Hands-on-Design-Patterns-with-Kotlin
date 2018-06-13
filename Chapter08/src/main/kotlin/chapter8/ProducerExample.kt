package chapter8

import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) {


    val publisher: ReceiveChannel<Int> = produce {
        for (i in 35 downTo 29) {
            send(i)
            delay(20)
        }
    }


    runBlocking {
        publisher.consumeEach {
            println("Got $it")
        }
    }


}