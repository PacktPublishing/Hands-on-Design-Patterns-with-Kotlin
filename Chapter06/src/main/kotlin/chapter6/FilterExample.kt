package chapter6

import com.sun.org.apache.xpath.internal.operations.Bool


fun main(args: Array<String>) {

    println(filterOdd((1..10).toList()))

    println(filter((1..10).toList()) {
        it % 2 != 0
    })

    println((1..10).toList().filter {
        it % 2 != 0
    })

    val n = listOf(1, 2, 3)

}

fun filterOdd(numbers: List<Int>): MutableList<Int> {
    val result = mutableListOf<Int>()

    for (n in numbers) {
        if (n % 2 != 0) {
            result.add(n)
        }
    }

    return result
}

fun filter(numbers: List<Int>, check: (Int)->Boolean): MutableList<Int> {
    val result = mutableListOf<Int>()

    for (n in numbers) {
        if (check(n)) {
            result.add(n)
        }
    }

    return result
}