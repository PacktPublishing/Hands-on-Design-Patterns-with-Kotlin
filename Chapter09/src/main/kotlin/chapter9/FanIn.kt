package chapter9

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.consumeEachIndexed
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import java.util.*

fun main(args: Array<String>) {

    val collector = Channel<String>()
    
    techBunch(collector)
    theFerge(collector)

    runBlocking {
        collector.consumeEachIndexed {
            println("${it.index} Got news from ${it.value}")
        }
    }
}


private fun techBunch(collector: Channel<String>) = launch {
    repeat(10) {
        delay(Random().nextInt(1000))
        collector.send("Tech Bunch")
    }
}

private fun theFerge(collector: Channel<String>) = launch {
    repeat(10) {
        delay(Random().nextInt(1000))
        collector.send("The Ferge")
    }
}