package chapter7

import io.reactivex.Observable
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import java.util.concurrent.TimeUnit


fun main(args: Array<String>) {

    replay()
}

private fun replay() {
    val list = (8..23).toList() // Some non trivial numbers
    val iterator = list.iterator()
    /*val o = Observable.intervalRange(0, list.size.toLong(), 0, 10, TimeUnit.MILLISECONDS).map {
        iterator.next()
    }.publish()*/

    val o = Observable.generate<Int> { 1 }.publish()

    val subject = AsyncSubject.create<Int>()

    o.subscribe(subject)

    o.connect() // Start publishing

    Thread.sleep(20)

    println("S1 subscribes")
    subject.subscribe {
        println("S1 $it")
    }
    println("S1 subscribed")

    Thread.sleep(10)

    println("S2 subscribes")
    subject.subscribe {
        println("S2 $it")
    }
    println("S2 subscribed")

    Thread.sleep(1000)
}

