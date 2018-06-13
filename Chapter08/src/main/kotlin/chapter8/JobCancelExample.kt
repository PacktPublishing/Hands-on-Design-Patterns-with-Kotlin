package chapter8

import kotlinx.coroutines.experimental.CancellationException
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.yield

fun main(args: Array<String>) {


    val cancellable = launch {
        try {
            for (i in 1..1000) {
                println("Cancellable: $i")
                yield()
            }
        }
        catch (e: CancellationException) {
            e.printStackTrace()
        }
    }

    val notCancellable = launch {
        for (i in 1..1000) {
            if (i % 100 == 0) {
                println("Not cancellable $i")
            }
        }
    }

    println("Canceling not cancellable")
    notCancellable.cancel()
    println("Canceling cancellable")
    cancellable.cancel()


    runBlocking {
        cancellable.join()
        notCancellable.join()
    }

}