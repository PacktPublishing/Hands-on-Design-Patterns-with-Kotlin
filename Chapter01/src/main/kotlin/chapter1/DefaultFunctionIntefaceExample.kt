package chapter1

interface Greeter {
    fun sayHello() {
        println("Hello")
    }
}

abstract class AbstractDungeonMaster(private val gameName : String) {
    open fun startGame() {
        println("Game $gameName has started!")
    }
}

open class DungeonMaster(gameName: String) :
        Greeter, AbstractDungeonMaster(gameName) {
}

class EvilDungeonMaster(private val awfulGame: String) : DungeonMaster(awfulGame) {
    override fun sayHello() {
        println("Prepare to die! Muwahaha!!!")
    }

    override fun startGame() {
        println("$awfulGame will be your last!")
    }
}

fun main(args : Array<String>) {
    val p = EvilDungeonMaster("Beholder Strikes Back")
    p.sayHello()
    p.startGame()
}