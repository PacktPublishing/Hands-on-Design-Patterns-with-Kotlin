package chapter6

fun main(args: Array<String>) {

    val letters = listOf("a", "b", "c", "d")

    println(repeatAll(letters)) // [aa, bb, cc, dd]

    println(letters.map {
        it.toUpperCase()
    })


    println(repeatSomething(letters) {
        it.toUpperCase()
    })



}

fun repeatAll(letters: List<String>): MutableList<String> {
    val repeatedLetters = mutableListOf<String>()

    for (l in letters) {
        repeatedLetters.add(l + l)
    }
    return repeatedLetters
}

fun <T> repeatSomething(input: List<T>, action: (T) -> T): MutableList<T> {
    val result = mutableListOf<T>()

    for (i in input) {
        result.add(action(i))
    }
    return result
}