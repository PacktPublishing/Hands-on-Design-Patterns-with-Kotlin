package chapter9

import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.selects.select

fun main(vararg args: String) {

    val batman = actor<String> {
        for (c in this) {
            println("Batman is beating some sense into $c")
            delay(100)
        }
    }

    val robin = actor<String> {
        for (c in this) {
            println("Robin is beating some sense into $c")
            delay(250)
        }
    }

    val j = launch {
        for (c in listOf("Jocker", "Bane", "Penguin", "Riddler", "Killer Croc")) {
            val result = select<Pair<String, String>> {
                batman.onSend(c) {
                    Pair("Batman", c)
                }
                robin.onSend(c) {
                    Pair("Robin", c)
                }
            }
            delay(90)
            println(result)
        }
    }

    runBlocking {
        j.join()
    }

}