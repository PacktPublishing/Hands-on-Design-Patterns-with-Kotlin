package chapter5.patternMatching

fun main(args: Array<String>) {
    val a1 = Cat()
    val a2 = Dog()

    println(getSound(a1))
    println(getSound(a2))
}

fun getSound(animal: Animal) = when(animal) {
    is Cat -> animal.purr()
    is Dog -> animal.bark()
    else -> throw RuntimeException()
}

class Cat : Animal {
    fun purr() = "Purr-purr"
}

class Dog : Animal {
    fun bark() = "Bark-bark"
}

interface Animal
