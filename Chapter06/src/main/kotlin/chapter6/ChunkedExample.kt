package chapter6

fun main(args: Array<String>) {

    val hugeList = (1..57_721).toList()

    // That will fail at runtime
    // dbCall(hugeList)
    val pageSize = 1000
    val pages = hugeList.size / pageSize

    for (i in 0..pages) {
        val from = i * pageSize
        val p = (i+1) * pageSize
        val to = minOf(p, hugeList.size)
        dbCall(hugeList.slice(from until to))
    }
/*
    hugeList.chunked(pageSize) {
        dbCall(it)
    }*/
}

fun dbCall(ids: List<Int>) {
    if (ids.size > 1000) {
        throw RuntimeException("Can't process more than 1000 ids at once")
    }

    println(ids.last())
}
