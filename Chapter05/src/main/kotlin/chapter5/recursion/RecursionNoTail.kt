package chapter5.recursion.bad


fun sumRec(i: Int, numbers: List<Int>): Long {
    return try {
        if (i == numbers.size) {
            0
        } else {
            numbers[i] + sumRec(i + 1, numbers)
        }
    } catch (e: StackOverflowError) {
        println("I was $i")
        0
    }
}

fun main(args: Array<String>) {

    val numbers = List(1_000_000) {it}
    println(sumRec(0,  numbers))
}