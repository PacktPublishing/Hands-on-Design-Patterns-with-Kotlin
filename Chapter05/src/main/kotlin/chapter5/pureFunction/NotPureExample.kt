package chapter5.pureFunction



fun sayHello() {
    println("Hello")
}

fun hello() = "Hello"

fun testHello(): Boolean {
    return "Hello" == hello()
}

fun <T> withoutFirst(list: List<T>): T {

    return ArrayList(list).removeAt(0)
}

fun main(args: Array<String>) {
    val list = mutableListOf(1, 2, 3)

    println(withoutFirst(list))
    println(withoutFirst(list))
}