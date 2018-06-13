package chapter10

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay


fun main(vararg args: String) {
    println("Name: ${getName()}")
}

fun getName() = async {
    delay(100)
    "Ruslan"
}