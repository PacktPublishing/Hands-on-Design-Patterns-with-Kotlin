package chapter1

fun main(args: Array<String>) {
    println("Hello, world!")

    val p = Person()
    p.name = "Alex"
    println(p.name)
}

class Person {
    var name : String = ""
        set(value) {
            this.name = value.toUpperCase()
        }
}