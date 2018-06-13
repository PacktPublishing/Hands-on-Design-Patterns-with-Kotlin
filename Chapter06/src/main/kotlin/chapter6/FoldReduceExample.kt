package chapter6

fun main(args: Array<String>) {

    val people = listOf(Person("Jane", "Doe", 19),
            Person("John", "Doe", 24),
            Person("John", "Smith", 23))

    println(people.reduce {p1, p2 ->
        Person("Combined", "Age", p1.age + p2.age)
    })

    println(people.reduce {p1, p2 ->
        if (p1.age > p2.age) { p1 } else { p2 }
    })

    println(people.drop(1).fold(people.first()) {p1, p2 ->
        Person("Combined", "Age", p1.age + p2.age)
    })
}