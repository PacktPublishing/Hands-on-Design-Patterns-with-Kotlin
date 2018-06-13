package chapter3

import java.util.stream.Stream
import kotlin.streams.toList


fun main(args : Array<String>) {

    cellPhone(charger(powerOutlet().toEUPlug()).toUsbTypeC())

    val l = listOf("a", "b", "c")

    streamProcessing(l.stream())

    val s = Stream.generate { 42 }

    println("Collecting elements")
    collectionProcessing(s.toList())
}

fun <T> collectionProcessing(c: Collection<T>) {
    for (e in c) {
        println(e)
    }
}

fun <T> streamProcessing(stream: Stream<T>) {
    // Do something with stream
}

fun USPlug.toEUPlug() : EUPlug {
    return object : EUPlug {
        // Do something to convert
    }
}

fun UsbMini.toUsbTypeC() : UsbTypeC {
    return object : UsbTypeC {
        // Do something to convert
    }
}

// Power outlet exposes USPlug interface
fun powerOutlet() : USPlug {
    return object : USPlug {}
}

// Charger accepts EUPlug interface and exposes UsbMini interface
fun charger(plug: EUPlug) : UsbMini {
    return object : UsbMini {}
}

fun cellPhone(chargeCable: UsbTypeC) {

}


interface UsbTypeC
interface UsbMini

interface EUPlug
interface USPlug
