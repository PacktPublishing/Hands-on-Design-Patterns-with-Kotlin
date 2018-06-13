package chapter10

fun main(vararg args: String) {
    multiply(3, 6)

    val l = (1..100).toList()

    l.filter{ it % 2 == 0 }
        .also { println(it) }
        .map { it * it }

}

fun multiply(a: Int, b: Int): Int = (a * b).also { println(it) }
