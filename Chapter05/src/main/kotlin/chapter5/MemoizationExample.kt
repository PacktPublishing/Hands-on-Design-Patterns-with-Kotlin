package chapter5


class Summarizer {
    private val resultsCache = mutableMapOf<List<Int>, Double>()

    fun summarize(numbers: List<Int>): Double {
        return resultsCache.computeIfAbsent(numbers, ::sum)
    }

    private fun sum(numbers: List<Int>): Double {
        return numbers.sumByDouble { it.toDouble() }
    }
}

fun main(args: Array<String>) {
    val l1 = listOf(1, 2, 3)
    val l2 = listOf(1, 2, 3)
    val l3 = listOf(1, 2, 3, 4)

    val summarizer = Summarizer()

    println(summarizer.summarize(l1))
    println(summarizer.summarize(l1))
    println(summarizer.summarize(l2))
    println(summarizer.summarize(l3))
}