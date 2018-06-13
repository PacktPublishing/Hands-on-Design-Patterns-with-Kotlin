package chapter6

fun main(args: Array<String>) {
    withMap()
    withMapTo()
}

fun withMap() {
    val letters = listOf("a", "B", "c", "D")
    val results = mutableListOf<String>()

    results.addAll(letters.map {
        it.toUpperCase()
    })

    results.addAll(letters.map {
        it.toLowerCase()
    })

    println(results)
}

fun withMapTo() {
    val letters = listOf("a", "B", "c", "D")
    val results = mutableListOf<String>()

    letters.mapTo(results) {
        it.toUpperCase()
    }

    letters.mapTo(results) {
        it.toLowerCase()
    }

    println(results)
}
