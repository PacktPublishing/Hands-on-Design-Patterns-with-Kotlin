package chapter9

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.selects.select
import java.util.*
import kotlin.system.measureTimeMillis

fun main(vararg args: String) = runBlocking {


   //take1()
    take2()
}

private suspend fun take2() {

    val stop = async {
        delay(600)
        true
    }

    val channel = Channel<Deferred<Int>>(10)

    repeat(10) {i ->
        channel.send(async {
            delay(i * 100)
            i
        })
    }
    println("DONE SENDING")

    runBlocking {
        for (i in 1..10) {
            select<Unit> {
                stop.onAwait {
                    channel.close()
                }
                channel.onReceive {
                    println(it.await())
                }
            }
        }
    }

}

private suspend fun take1() {
    val elements = 10
    val deferredChannel = Channel<Deferred<Int>>(elements)

    launch(CommonPool) {
        repeat(elements) { i ->
            println("$i sent")
            deferredChannel.send(async {
                delay(if (i == 0) 1000 else 10)
                i
            })
        }
    }

    val time = measureTimeMillis {
        repeat(elements) {
            val result = select<Int> {
                deferredChannel.onReceive {
                    select {
                        it.onAwait { it }
                    }
                }
            }
            println(result)
        }
    }

    println("Took ${time}ms")
}
