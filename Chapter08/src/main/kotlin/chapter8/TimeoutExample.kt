package chapter8

import kotlinx.coroutines.experimental.*
import java.util.*
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) = runBlocking {
        val coroutine = async {
            withTimeout(500, TimeUnit.MILLISECONDS) {
                try {
                    val time = Random().nextInt(1000)

                    println("It will take me $time to do")

                    delay(time)

                    println("Returning profile")
                    "Profile"
                }
                catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    val result = try {
        coroutine.await()
    }
    catch (e: TimeoutCancellationException) {
        "No Profile"
    }

    println(result)
}