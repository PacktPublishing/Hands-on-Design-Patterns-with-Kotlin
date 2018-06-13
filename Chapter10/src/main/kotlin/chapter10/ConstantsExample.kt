package chapter10


fun main(vararg args: String) {

    println(Spock.SENSE_OF_HUMOR)

    println(SensesOfHumor.SPOCK)
}

class Spock {
    companion object {
        const val SENSE_OF_HUMOR = "NONE"
    }
}

object SensesOfHumor {
    const val SPOCK = "NONE"
}