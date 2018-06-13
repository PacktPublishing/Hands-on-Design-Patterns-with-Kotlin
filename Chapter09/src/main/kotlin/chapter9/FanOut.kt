package chapter9

import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) {


    val producer = producePages()

    val consumers = List(10) {
        consumePages(producer)
    }

    runBlocking {
        consumers.forEach {
            it.await()
        }
    }

}


private fun producePages() = produce {
    for (i in 1..10_000) {
        for (c in 'a'..'z') {
            send(i to "page$c")
        }
    }
}

private fun consumePages(channel: ReceiveChannel<Pair<Int, String>>) = async {
    for (p in channel) {
        println(p)
    }
}