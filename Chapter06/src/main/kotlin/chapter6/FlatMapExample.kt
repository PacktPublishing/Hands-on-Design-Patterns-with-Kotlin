package chapter6

fun main(args: Array<String>) {

    val listOfLists = listOf(listOf(1, 2),
            listOf(3, 4, 5), listOf(6, 7, 8))

    println(listOfLists)
    val results = mutableListOf<Int>()

    for (l in listOfLists) {
        results.addAll(l)
    }

    println(results)


    println(listOfLists.flatten())

    println(listOfLists.flatMap {
        it.map { it.toDouble() }
    })

    val setOfListsOfSets = setOf(
            listOf(setOf(1, 2), setOf(3, 4, 5), setOf(6, 7, 8)),
            listOf(setOf(9, 10), setOf(11, 12))
    )

    println(setOfListsOfSets)

    println(setOfListsOfSets.flatten())

    println(setOfListsOfSets.flatten().flatten())

    //println(setOfListsOfSets.flatten().flatten().flatten())


}