package chapter5.immutability

import java.util.*
import kotlin.concurrent.thread


fun main(args: Array<String>) {


    val counter = AverageScore()

    thread(isDaemon = true) {
        while(true) counter.gamesPlayed = 0
    }

    for (i in 1..1_000) {
        counter.totalScore += Random().nextInt(100)
        counter.gamesPlayed++

        println(counter.average)
    }
}


data class AverageScore(var totalScore: Int = 0,
                        var gamesPlayed: Int = 0) {
    val average: Int
        get() = if (gamesPlayed <= 0)
                    0
                else
                    totalScore / gamesPlayed
}