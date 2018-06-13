package chapter9

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.channels.filter
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.dom.asElementList
import org.w3c.dom.Document
import org.xml.sax.InputSource
import java.io.StringReader
import java.util.concurrent.TimeUnit
import kotlin.coroutines.experimental.CoroutineContext

fun main(args: Array<String>) {


    val pagesProducer = producePages()

    val domProducer = produceDom(pagesProducer)

    val titleProducer = produceTitles(domProducer)

   /* runBlocking {
        titleProducer.consumeEach {
            println(it)
        }
    }*/


    runBlocking {
        producePages().dom().titles().consumeEach {
            println(it)
        }
    }

}

private fun ReceiveChannel<Document>.titles(): ReceiveChannel<String> {
    val channel = this
    fun getTitles(dom: Document): List<String> {
        val h1 = dom.getElementsByTagName("H1")
        return h1.asElementList().map {
            it.textContent
        }
    }

    return produce {
        for (page in channel) {
            for (t in getTitles(page)) {
                send(t)
            }
        }
    }
}

private fun ReceiveChannel<String>.dom(): ReceiveChannel<Document> {
    val channel = this
    return produce() {
        for (p in channel) {
            send(kotlinx.dom.parseXml(p.toSource()))
        }
    }
}

fun numbersFrom(context: CoroutineContext = CommonPool, start: Int) = produce<Int>(context) {
    var x = start
    while (true) send(x++) // infinite stream of integers from start

    this.coroutineContext
}

fun filter(context: CoroutineContext, numbers: ReceiveChannel<Int>, prime: Int) = produce<Int>(context) {
    for (x in numbers) if (x % prime != 0) send(x)
}

fun produceTitles(parsedPages: ReceiveChannel<Document>) = produce {
    fun getTitles(dom: Document): List<String> {
        val h1 = dom.getElementsByTagName("H1")
        return h1.asElementList().map {
            it.textContent
        }
    }

    for (page in parsedPages) {
        for (t in getTitles(page)) {
            send(t)
        }
    }
}

private fun producePages() = produce {
    fun getPages(): List<String> {
        // This should actually fetch something
        return listOf("<html><body><H1>Cool stuff</H1></body></html>",
                "<html><body><H1>Event more stuff</H1></body></html>").shuffled()
    }
    while (this.isActive) {
        val pages = getPages()
        for (p in pages) {
            send(p)
        }
        delay(TimeUnit.SECONDS.toMillis(5))
    }
}

fun produceDom(pages: ReceiveChannel<String>) = produce {

    fun parseDom(page: String): Document {
        return kotlinx.dom.parseXml(page.toSource())
    }

    for (p in pages) {
        send(parseDom(p))
    }
}

private fun String.toSource(): InputSource {
    return InputSource(StringReader(this))
}
