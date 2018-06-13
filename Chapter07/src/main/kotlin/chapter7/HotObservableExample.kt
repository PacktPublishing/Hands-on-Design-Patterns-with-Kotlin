package chapter7

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.Subject

fun main(args: Array<String>) {



    val iterator = (1..15).iterator()

    val publisher = Observable.create<Int> {
        while (iterator.hasNext()) {
            val nextNumber = iterator.next()
            it.onNext(nextNumber)
        }
    }


    publisher.subscribeOn(Schedulers.newThread()).subscribe {
        println("S1: $it")
        Thread.sleep(10)
    }

    Thread.sleep(50)

    publisher.subscribeOn(Schedulers.newThread()).subscribe {
        println("S2: $it")
        Thread.sleep(10)
    }

    Thread.sleep(50)
}