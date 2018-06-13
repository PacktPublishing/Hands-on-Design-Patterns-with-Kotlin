package chapter10


fun main(vararg args: String) {

}

class MyClass(val a: String, val b: Int, val c: Long) {

    constructor(a: String, b: Int) : this(a, b, 0)

    constructor(a: String) : this(a, 1)

    constructor() : this("Default")
}

class BetterClass(val a: String = "Default",
                  val b: Int = 1,
                  val c: Long = 0)