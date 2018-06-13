package chapter8

import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.selects.select
import java.util.*

fun main(args: Array<String>) = runBlocking {


    val firstProducer = produce {
        for (c in 'a'..'z') {
            delay(Random().nextInt(100))
            send(c.toString())
        }
        close()
    }

    val secondProducer = produce {
        for (c in 'A'..'Z') {
            delay(Random().nextInt(100))
            send(c.toString())
        }
        close()
    }

    while(true) {
        val result = select<String?> {
            firstProducer.onReceiveOrNull {
                it
            }
            secondProducer.onReceiveOrNull {
                it
            }
        }

        if (result == null) {
            break
        }
        else {
            println(result)
        }
    }
}