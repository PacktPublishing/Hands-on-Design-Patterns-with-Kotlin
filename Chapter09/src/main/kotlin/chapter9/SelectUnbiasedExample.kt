package chapter9

import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.selects.select
import kotlinx.coroutines.experimental.selects.selectUnbiased
import java.util.concurrent.ConcurrentHashMap

fun main(vararg args: String) = runBlocking {


    val repeats = 10_000
    val p1 = producer("A", repeats)
    val p2 = producer("B", repeats)
    val p3 = producer("C", repeats)

    val results = ConcurrentHashMap<String, Int>()
    repeat(repeats) {
        val result = selectUnbiased<String> {
            p1.onReceive { it }
            p2.onReceive { it }
            p3.onReceive { it }
        }

        results.compute(result) { k, v ->
            if (v == null) {
                1
            }
            else {
                v + 1
            }
        }
    }

    println(results)
}

private fun producer(name: String, repeats: Int) = produce {
    repeat(repeats) {
        send(name)
    }
}