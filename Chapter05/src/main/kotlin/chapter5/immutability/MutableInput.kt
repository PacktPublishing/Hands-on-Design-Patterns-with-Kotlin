package chapter5.immutability


fun main(args: Array<String>) {
    fun <T> printAndClear(list: MutableList<T>) {
        for (e in list) {
            println(e)
            list.remove(e)
        }
    }
    printAndClear(mutableListOf("a", "b", "c"))
}

