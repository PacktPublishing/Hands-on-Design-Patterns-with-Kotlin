package chapter4.memento


fun main(args: Array<String>) {
    val michael = Manager()

    val captured = michael.Thought().captureThought()
    michael.anotherThought()
    michael.whatAreYouThinking()
    println(captured)
}


class Manager {
    private var lastThought = "Should get some coffee"
    private var repeatThat = 3
    private var thenHesitate = "Or maybe tea?"
    private var secretThought = "No, coffee it is"
    fun whatAreYouThinking() {
        for (i in 1..repeatThat) {
            println(lastThought)
        }
        println(thenHesitate)
    }

    inner class Thought {
        fun captureThought(): CapturedThought {
            return CapturedThought(lastThought, repeatThat, thenHesitate, secretThought)
        }

        fun rewindThought(previousThought: CapturedThought) {
            with(previousThought) {
                lastThought = thought
                repeatThat = repeat
                thenHesitate = hesitate
                secretThought = secret
            }
        }
    }

    data class CapturedThought(val thought: String,
                               val repeat: Int,
                               val hesitate: String,
                               val secret: String)

    fun anotherThought() {
        lastThought = "Tea would be better"
        repeatThat = 2
        thenHesitate = "But coffee is also nice"
        secretThought = "Big latte would be great"
    }
}

