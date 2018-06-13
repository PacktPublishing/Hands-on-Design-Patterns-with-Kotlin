package chapter6

fun main(args: Array<String>) {

    val numbers = (1..5)

    println(numbers.joinToString { "$it"})

    println(numbers.joinToString(separator = "#") { "$it"})
}