package chapter6

fun main(args: Array<String>) {
    val numbers = (1..10).toList()

    println(numbers.toList())

    println(numbers.slice((0..3)))

    println(numbers.subList(0, 3))

}