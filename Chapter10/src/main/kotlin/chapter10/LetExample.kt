package chapter10

import java.util.*

fun main(vararg args: String) {

    val sometimesNull = if (Random().nextBoolean()) "not null" else null

    sometimesNull.let {
        println("It was $it this time")
    }

    val alwaysNull = null

    alwaysNull.let {
        println("It was $it this time")
    }

    val justAString = "string"

    val numberReturned = justAString.let {
        println(it)
        it.length
    }

    val n = justAString.run {
        length
    }
}