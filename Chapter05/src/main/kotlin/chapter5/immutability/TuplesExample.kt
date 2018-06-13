package chapter5.immutability


fun main(args: Array<String>) {

    val pair = "a" to 1

    val (key, value) = pair

    for (p in mapOf(1 to "Sunday", 2 to "Monday")) {
        println("${p.key} ${p.value}")
    }

}