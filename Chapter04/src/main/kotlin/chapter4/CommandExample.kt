package chapter4


fun main(args: Array<String>) {

    val s = Soldier()
    s.appendMove(20, 0)
        .appendMove(20, 20)
        .appendMove(5, 20)
        .execute()
}

val moveGenerator = fun (s: Soldier,
                        x: Int,
                        y: Int): Command {
    return fun() {
        s.move(x, y)
    }
}

class Soldier {
    private val commands = mutableListOf<Command>()

    // This will be triggered from the outside once in a while
    fun execute() {
        while (!commands.isEmpty()) {
            val command = commands.removeAt(0)
            command.invoke()
        }
    }

    fun move(x: Int, y: Int) {
        println("Moving to ($x, $y)")
    }

    fun attack(x: Int, y: Int) {
        println("Attacking ($x, $y)")
    }

    fun appendMove(x: Int, y: Int) = apply {
        commands.add(moveGenerator(this, x, y))
    }
}

typealias Command = ()->Unit
