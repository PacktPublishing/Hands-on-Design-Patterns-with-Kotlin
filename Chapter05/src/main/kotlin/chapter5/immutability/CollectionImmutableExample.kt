package chapter5.immutability

import java.util.*
import kotlin.concurrent.thread


fun main(args: Array<String>) {

    val counter = ImmutableScoreCollector(List(1_000) {
        Random().nextInt(100)
    })

    thread(isDaemon = true, name="Maleficent") {
        //while(true) counter.scores.clear()
    }
}


data class ImmutableScoreCollector(val scores: List<Int>)