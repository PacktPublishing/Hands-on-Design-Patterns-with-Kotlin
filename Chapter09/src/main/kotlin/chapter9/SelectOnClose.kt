package chapter9

import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.selects.select
import kotlinx.coroutines.experimental.selects.selectUnbiased

fun main(vararg args: String) {

    take1()
 //  take2()
}

private fun take1() {
    val p1 = produce {
        repeat(10) {
            send("A$it")
        }
    }

    val p2 = produce {
        repeat(5) {
            send("B$it")
        }
    }


    runBlocking {
        repeat(15) {
            val result = selectUnbiased<String> {
                p1.onReceiveOrNull {
                    it ?: throw RuntimeException()
                }
                p2.onReceiveOrNull {
                    it ?: "p2 closed"
                }
            }

            println(result)
        }
    }
}

private fun take2() {
    val p1 = produce {
        repeat(10) {
            send("A$it")
        }
    }

    val p2 = produce {
        repeat(5) {
            send("B$it")
        }
    }


    runBlocking {
        var count = 0
        while (count < 15) {
            val result = selectUnbiased<String?> {
                p1.onReceiveOrNull {
                    it
                }
                p2.onReceiveOrNull {
                    it
                }
            }

            if (result != null) {
                println(result)
                count++
            }
        }
    }
}
