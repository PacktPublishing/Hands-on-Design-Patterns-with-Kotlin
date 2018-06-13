package chapter5.immutability

import java.util.*
import kotlin.concurrent.thread


fun main(args: Array<String>) {
    val counter = ScoreCollector()

    thread(isDaemon = true, name="Maleficent") {
        while(true) counter.scores.clear()
    }

    for (i in 1..1_000) {
        counter.scores += Random().nextInt(100)

        println(counter.scores.sumBy { it } / counter.scores.size)
    }

}


data class ScoreCollector(val scores: MutableList<Int> = mutableListOf())