package chapter10

import kotlin.reflect.KClass

fun main(vararg args: String) {

    printIfSameType(Int::class, 1)
    printIfSameType(Int::class, 2L)
    printIfSameTypeReified<Int>(3)
    printIfSameTypeReified<Int>(4L)
    printIfSameTypeReified<Long>(5)
    printIfSameTypeReified<Long>(6L)

    printList(listOf(1, 2, 3, 4))
    printList(listOf(1L, 2L, 3L, 4L))
    printList(listOf("a", "b", "c"))
}

fun <T: Number> printIfSameType(clazz: KClass<T>, a: Number) {
    if (clazz.isInstance(a) ) {
        println(a)
    }
}

inline fun <reified T> printIfSameTypeReified(a: Number) {
    if (a is T) {
        println(a)
    }
}
inline fun <reified T : Any> printList(list: List<T>) {
    when (T::class) {
        Int::class -> println("This is a list of Ints")
        Long::class -> println("This is a list of Longs")
        else -> println("This is a list of something else")
    }

    println(list)
}
