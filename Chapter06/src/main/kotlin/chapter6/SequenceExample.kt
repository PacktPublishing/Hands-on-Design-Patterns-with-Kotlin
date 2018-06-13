package chapter6

fun main(args: Array<String>) {

    val seq = generateSequence(1) { it + 1 }

    seq.take(100).forEach {
        println(it)
    }

    val finiteSequence = generateSequence(1) {
        if (it < 100) it + 1 else null
    }

    finiteSequence.forEach {
        println(it)
    }

    finiteSequence.filter {
        if (it % 3 == 0) {
            print("Fiz")
            true
        }
        else {
            false
        }
    }.filter {
        if (it % 5 == 0) {
            println("Baz")
            true
        }
        else {
            false
        }
    }.onEach{ println(it) }.toList()


}