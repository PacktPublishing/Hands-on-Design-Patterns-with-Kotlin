package chapter4.mediator


interface QA {
    fun doesMyCodeWork(): Boolean
}

interface Parrot {
    fun isEating(): Boolean
    fun isSleeping(): Boolean
}

object Kenny : QA, Parrot {
    val peer = George

    override fun isSleeping(): Boolean {
        return false
    }

    override fun isEating(): Boolean {
        return false
    }

    override fun doesMyCodeWork(): Boolean {
        if (isSleeping()) {
            throw RuntimeException()
        }
        if (isEating()) {
            throw RuntimeException()
        }
        return true
    }
}

object Brad : QA, Parrot {

    val senior = Brad

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

object George : QA, Owl {
    val mate = Brad

    override fun doesMyCodeWork(): Boolean {
        return true && mate.doesMyCodeWork()
    }

    override fun isWatchingFootball(): Boolean {
        return false
    }
}

interface Translator {
    fun areAllTranslationsCorrect(): Boolean
}

interface Kiwi

object Sandra : Translator, Kiwi {
    override fun areAllTranslationsCorrect(): Boolean {
        return true
    }
}


interface Owl {
    fun isWatchingFootball(): Boolean
}

class MyMind {
    val qa = Kenny
    val qa2 = Brad
    val qa3 = George
    val translator = Sandra

    fun taskCompleted(isMajorRelease: Boolean) {
        if (!Kenny.isEating() && !Kenny.isSleeping()) {
            println(Kenny.doesMyCodeWork())
        }
        else if (!Brad.isEating() && !Brad.isSleeping()) {
            println(Brad.doesMyCodeWork())
        }
        else if (!George.isWatchingFootball()) {
            println(George.doesMyCodeWork())
        }

        if (isMajorRelease) {
            println(Sandra.areAllTranslationsCorrect())
        }
    }
}