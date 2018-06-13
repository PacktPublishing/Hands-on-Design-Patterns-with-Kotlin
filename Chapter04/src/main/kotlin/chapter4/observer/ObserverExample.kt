package chapter4.observer



fun main(args: Array<String>) {


    val catTheConductor = Cat()

    val bat = Bat()
    val dog = Dog()
    val turkey = Turkey()

    catTheConductor.joinChoir(bat::screech)
    catTheConductor.joinChoir(dog::howl)
    catTheConductor.joinChoir(dog::bark)
    catTheConductor.joinChoir(turkey::gobble)

    catTheConductor.conduct(LowMessage(2))
    println()

    catTheConductor.leaveChoir(bat::screech)
    catTheConductor.conduct(HighMessage(1))
}

typealias Times = Int

enum class SoundPitch {HIGH, LOW}
interface Message {
    val repeat: Times
    val pitch: SoundPitch
}
data class LowMessage(override val repeat: Times) : Message {
    override val pitch = SoundPitch.LOW
}

data class HighMessage(override val repeat: Times) : Message {
    override val pitch = SoundPitch.HIGH
}
class Cat {
    private val participants = mutableMapOf<(Message)->Unit, (Message)->Unit>()

    fun joinChoir(whatToCall: (Message)->Unit) {
        participants.put(whatToCall, whatToCall)
    }

    fun leaveChoir(whatNotToCall: (Message)->Unit) {
        participants.remove(whatNotToCall)
    }

    fun conduct(message: Message) {
        for (p in participants.values) {
            p(message)
        }
    }
}

class Bat {
    fun screech(message: Message) {
        when (message) {
            is HighMessage -> {
                for (i in 1..message.repeat) {
                    println("${message.pitch} Eeeeeee")
                }
            }
            else -> println("Can't :(")
        }
    }
}

class Turkey {
    fun gobble(message: Message) {
        for (i in 1..message.repeat) {
            println("${message.pitch} Gob-gob")
        }
    }
}

class Dog {
    fun bark(message: Message) {
        for (i in 1..message.repeat) {
            println("${message.pitch} Woof")
        }
    }

    fun howl(message: Message) {
        for (i in 1..message.repeat) {
            println("${message.pitch} Auuuu")
        }
    }
}