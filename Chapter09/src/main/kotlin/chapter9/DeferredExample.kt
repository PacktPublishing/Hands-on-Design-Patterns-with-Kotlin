package chapter9

import kotlinx.coroutines.experimental.*
import java.util.*

fun main(vararg args: String) = runBlocking {

    val deferred = CompletableDeferred<String>()

    launch {
        try {
            delay(100)
            if (Random().nextBoolean()) {
                deferred.complete("OK")
            }
            else {
                deferred.completeExceptionally(RuntimeException())
            }
        }
        catch (e : JobCancellationException) {
            println("I was cancelled")
        }
    }

    deferred.cancel()
    println(deferred.await())
}