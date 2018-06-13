package chapter6

fun main(args: Array<String>) {

    val numbers = (1..5).toList()
    println(numbers.drop(2))
    println(numbers)

    println(numbers.dropLast(2))


    val readings = listOf(-7, -2, -1, -1, 0, 1, 3, 4)

    println(readings.dropWhile {
        it <= 0
    })
}