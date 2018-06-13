package chapter9

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.selects.select
import kotlinx.coroutines.experimental.selects.whileSelect

fun main (vararg  a: String) = runBlocking {


    val channel = Channel<String>()
    val actor = activeActor(channel)

    val j1 = launch {
        for (i in 42..53) {
            actor.send(i)
        }
        actor.close()
    }

    val j2 = launch {
        for (i in channel) {
            println(i)
        }
    }

    j1.join()
    j2.join()
}

fun activeActor(out: SendChannel<String>) = actor<Int> {
    for (i in this) {
        out.send(i.toString().reversed())
    }
    out.close()
}