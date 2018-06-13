package chapter10

import java.io.BufferedReader
import java.io.FileReader


fun main(vararg args : String) {


    val br = BufferedReader(FileReader(""))

    br.use {
        println(it.readLine())
    }
}