package chapter6

fun main(args: Array<String>) {


    val people = listOf(Person("Jane", "Doe", 19),
            Person("John", "Doe", 22),
            Person("John", "Smith", 23))

    println(people.find {
        it.firstName == "John"
    })

    println(people.findLast {
        it.firstName == "John"
    })
}

fun <T> List<T>.find(check: (T) -> Boolean): T? {
    for (p in this) {
        if (check(p)) {
            return p
        }
    }
    return null
}

data class Person(val firstName: String,
                  val lastName: String,
                  val age: Int)