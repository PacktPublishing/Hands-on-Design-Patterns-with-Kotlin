package chapter7

import io.reactivex.Observable

fun main(args: Array<String>) {

    val publisher = Observable.create<Int> {
        for (i in 1..10_000) {
            it.onNext(i)
        }

    }

    publisher.subscribe {
        println("S1 $it")
    }

    publisher.subscribe {
        println("S2 $it")
    }

}