package chapter7

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

fun main(args: Array<String>) {


    cold()
    hotSource()
}

fun hotSource() {
    val dataSource = Observable.fromIterable((1..3)).publish()

    val multicast = PublishSubject.create<Int>()

    dataSource.subscribe(multicast)

    println("S1 subscribes")
    multicast.subscribe {
        println("S1 $it")
    }
    println("S1 subscribed")

    println("S2 subscribes")
    multicast.subscribe {
        println("S2 $it")
    }
    println("S2 subscribed")

    dataSource.connect()

    Thread.sleep(1000)
}

fun cold() {
    val dataSource = Observable.fromIterable((1..3))


    val multicast = PublishSubject.create<Int>()


    multicast.subscribe {
        println("S1 $it")
    }

    multicast.subscribe {
        println("S2 $it")
    }

    dataSource.subscribe(multicast)

    Thread.sleep(1000)
}
