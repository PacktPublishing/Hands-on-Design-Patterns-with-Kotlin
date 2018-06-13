package chapter4.mediator.good

import chapter4.mediator.George
import chapter4.mediator.Parrot
import chapter4.mediator.QA


interface Manager {
    fun isAllGood(majorRelease: Boolean): Boolean
}

object Michael: Canary, Manager {
    val kenny = Kenny(this)
    val brad = Brad(this)

    override fun isAllGood(majorRelease: Boolean): Boolean {
        if (!kenny.isEating() && !kenny.isSleeping()) {
            println(kenny.doesMyCodeWork())
        }
        else if (!brad.isEating() && !brad.isSleeping()) {
            println(brad.doesMyCodeWork())
        }
        return true
    }
}

interface Canary {

}

class Kenny(private val manager: Manager) : QA, Parrot {

    override fun isSleeping(): Boolean {
        return false
    }

    override fun isEating(): Boolean {
        return false
    }

    override fun doesMyCodeWork(): Boolean {
        return true
    }
}

class Brad(private val manager: Manager) : QA, Parrot {


    override fun isSleeping(): Boolean {
        return false
    }

    override fun isEating(): Boolean {
        return false
    }

    override fun doesMyCodeWork(): Boolean {
        return true
    }
}

class MyPeacefulMind(private val manager: Manager) {
    fun taskCompleted(isMajorRelease: Boolean) {
        println(manager.isAllGood(isMajorRelease))
    }
}