package chapter8

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.yield

fun main(args: Array<String>)  {


    val j = launch(CommonPool) {
        for (i in 1..10_000) {
            if (i % 1000 == 0) {
                println(i)
                yield()
            }
        }
    }

    runBlocking {
        j.join()
    }
}