package chapter9

import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.sync.Mutex
import kotlinx.coroutines.experimental.sync.withLock
import kotlinx.coroutines.experimental.yield

fun main(vararg args: String) {


    var counter = 0
    val mutex = Mutex()

    val jobs = List(10) {
        launch {
            repeat(1000) {
                try {
                    mutex.lock()
                    counter++
                }
                finally {
                    mutex.unlock()
                }

                yield()
            }
        }
    }

    runBlocking {
        jobs.forEach {
            it.join()
        }
        println(counter)
    }

}