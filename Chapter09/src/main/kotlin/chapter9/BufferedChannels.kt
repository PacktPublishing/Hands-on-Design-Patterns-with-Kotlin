package chapter9

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking


fun main(vararg args: String) = runBlocking {


    val channel = Channel<Int>(5)

    val j = launch {
        for (i in 1..10) {
            channel.send(i)
            println("Sent $i")
        }
    }

    j.join()

    val actor = actor<Int>(capacity = 5) {

    }

    val producer = produce<Int>(capacity = 10) {

    }
}