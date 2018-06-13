package chapter9

import kotlinx.coroutines.experimental.*

fun main(vararg a: String) = runBlocking {

   //take2()
    //take3()
    unconfined()
}

private fun unconfined() = runBlocking {
    val r1 = async(Unconfined) {
        for (i in 1..1000) {
            println(Thread.currentThread().name)
            delay(1)
        }
    }

    r1.await()
}

private fun take2() = runBlocking {
    val r1 = async {
        for (i in 1..1000) {
            val parentThread = Thread.currentThread().name
            launch(coroutineContext) {
                println(Thread.currentThread().name == parentThread)
            }
            yield()
        }
    }

    r1.await()
}

private fun take3() = runBlocking {
    val pool = newFixedThreadPoolContext(2, "My Own Pool")
    val r1 = async(pool) {
        for (i in 1..1000) {
            println(Thread.currentThread().name)
            yield()
        }
    }

    r1.await()
    pool.close()
}