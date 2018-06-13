package chapter6

fun main(args: Array<String>) {
    val people = listOf(Person("Jane", "Doe", 19),
            Person("John", "Doe", 24),
            Person("John", "Smith", 23))

    println(people.sortedBy { it.age })

    println(people.sortedByDescending { it.lastName })

    println(people.sortedWith(compareBy({it.lastName}, {it.age})))
}