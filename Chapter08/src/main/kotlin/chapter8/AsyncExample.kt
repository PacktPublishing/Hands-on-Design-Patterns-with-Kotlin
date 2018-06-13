package chapter8

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.*

fun main(args: Array<String>) {


    val userProfile = async {
        delay(Random().nextInt(100))
        "Profile"
    }

    val userHistory = async {
        delay(Random().nextInt(200))
        listOf(1, 2, 3)
    }

    runBlocking {
        println("User profile is ${userProfile.await()} and his history is ${userHistory.await()}")
    }

}