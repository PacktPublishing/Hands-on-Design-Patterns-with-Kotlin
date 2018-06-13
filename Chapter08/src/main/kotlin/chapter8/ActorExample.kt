package chapter8

import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) {
    val a = actor<String> {
        while (!isClosedForReceive) {
            println(receive().repeat(10))
        }
    }

    runBlocking {
        for (i in 'a'..'z') {
            a.send(i.toString())
        }
        a.close()
    }

    val b = actor<String> {

        var next = receiveOrNull()

        while (next != null) {
            println(next.toUpperCase())
            next = receiveOrNull()
        }
    }

    runBlocking {
        for (i in 'a'..'z') {
            b.send(i.toString())
        }
        b.close()
    }

}