package chapter3

import java.io.BufferedOutputStream
import java.io.File

fun main(args: Array<String>) {


    val catImage34 = CatImage("https://funny.cats/thumb-34.jpg", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Cat03.jpg/1200px-Cat03.jpg")

    println(catImage34.image.toPath())
    catImage34.image
    catImage34.image
}



data class CatImage(private val thumbnailUrl: String, private val url: String) {
    val image: java.io.File by lazy {
        println("Fetching image over network")
        val f = File.createTempFile("cat", ".jpg")
        java.net.URI.create(url).toURL().openStream().use {
            it.copyTo(BufferedOutputStream(f.outputStream()))
        }.also { println("Done fetching") }
        f
    }
}