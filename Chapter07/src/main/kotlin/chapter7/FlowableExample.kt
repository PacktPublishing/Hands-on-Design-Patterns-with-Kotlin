package chapter7

import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.stream.IntStream
import io.reactivex.subjects.PublishSubject
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.Callable
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicInteger


fun main(args: Array<String>) {


    //lambda()
    //obj()
    generate()
    generateFunctional()
}

fun generateFunctional() {
    val latch = CountDownLatch(1)
    val source = Flowable.generate<String, State>(
            Callable<State> { State(0, System.currentTimeMillis()) },
            BiFunction<State, Emitter<String>, State> { state, emitter ->
                emitter.onNext(UUID.randomUUID().toString())

                val count = state.count + 1
                var startTime = state.startTime
                if (count == 10_000_000) {
                    emitter.onComplete()
                    latch.countDown()
                }

                if (count % 100_000 == 0) {
                    println("Produced ${count} events in ${System.currentTimeMillis() - startTime}ms")
                    startTime = System.currentTimeMillis()
                }

                State(count, startTime)
            }
    )

    source.observeOn(Schedulers.newThread()).subscribe(object: Subscriber<String> {
        lateinit var subscription : Subscription

        override fun onNext(t: String) {
            t.repeat(500)

            this.subscription.request(1)
        }

        override fun onError(t: Throwable?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onSubscribe(s: Subscription) {
            this.subscription = s
            this.subscription.request(1)
        }

        override fun onComplete() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    })

    latch.await()
}

data class State(val count: Int, val startTime: Long)
private fun generate() {

    val latch = CountDownLatch(1)
    val count = AtomicInteger(0)
    var startTime = System.currentTimeMillis()
    val source = Flowable.generate<String> {
        it.onNext(UUID.randomUUID().toString())

        if (count.incrementAndGet() == 10_000_000) {
            it.onComplete()
            latch.countDown()
        }

        if (count.get() % 100_000 == 0) {
            println("Produced ${count.get()} events in ${System.currentTimeMillis() - startTime}ms")
            startTime = System.currentTimeMillis()
        }
    }

    source.observeOn(Schedulers.newThread()).subscribe(object: Subscriber<String> {
        lateinit var subscription : Subscription

        override fun onNext(t: String) {
            t.repeat(500)

            this.subscription.request(1)
        }

        override fun onError(t: Throwable?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onSubscribe(s: Subscription) {
            this.subscription = s
            this.subscription.request(1)
        }

        override fun onComplete() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    })

    latch.await()
}

private fun obj() {
    val latch = CountDownLatch(1)
    val source = Flowable.create<String> ({
        var startProducing = System.currentTimeMillis()
        for (i in 1..10_000_000) {
            it.onNext(UUID.randomUUID().toString())

            if (i % 100_000 == 0) {
                println("Produced $i events in ${System.currentTimeMillis() - startProducing}ms")
                startProducing = System.currentTimeMillis()
            }
        }
        it.onComplete()
        latch.countDown()
    }, BackpressureStrategy.MISSING).onBackpressureBuffer(10_000)


    source.observeOn(Schedulers.newThread())
            .subscribe(object : Subscriber<String> {
        lateinit var subscription : Subscription
        val counter = AtomicInteger(0)

        override fun onSubscribe(s: Subscription) {
            this.subscription = s
            this.subscription.request(100)
        }

        override fun onNext(t: String) {
            t.repeat(500)

            println(counter.get())
            this.subscription.request(1)

            if (counter.incrementAndGet() % 100_000 == 0) {
                println("Consumed ${counter.get()} events")
            }
        }

        override fun onError(t: Throwable) {
            t.printStackTrace()
        }

        override fun onComplete() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    })

    latch.await()
}

private fun lambda() {
    val latch = CountDownLatch(1)
    val source = Flowable.create<String> ({
        var startProducing = System.currentTimeMillis()
        for (i in 1..10_000_000) {
            it.onNext(UUID.randomUUID().toString())

            if (i % 100_000 == 0) {
                println("Produced $i events in ${System.currentTimeMillis() - startProducing}ms")
                startProducing = System.currentTimeMillis()
            }
        }
        it.onComplete()
        latch.countDown()
    }, BackpressureStrategy.DROP)


    val counter = AtomicInteger(0)
    source.observeOn(Schedulers.newThread())
            .subscribe( {
                it.repeat(500)
                if (counter.incrementAndGet() % 100_000 == 0) {
                    println("Consumed ${counter.get()} events")
                }
            } , {
                println(it)
            }, {
                println("Done consuming")
            })


    /*  Flowable.range(1, 1000000)
              .observeOn(Schedulers.computation())
              .subscribe({ println(it)
                  Thread.sleep(100)
              }, {println(it)})*/

    latch.await()
}