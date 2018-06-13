package chapter4


fun main(args: Array<String>) {

}

class Snail : WhatCanHappen {
    private var mood: Mood = Still()
    private var healthPoints = 10

    override fun seeHero() {
        mood = when(mood) {
            is Still -> Aggressive()
            is Aggressive -> mood
            is Retreating -> mood
            is Dead -> mood
        }
    }

    override fun getHit(pointsOfDamage: Int) {
        healthPoints -= pointsOfDamage

        mood = when {
            (healthPoints <= 0) -> Dead()
            mood is Aggressive -> Retreating()
            else -> mood
        }
    }

    override fun timePassed() {
        mood = when(mood) {
            is Retreating -> Aggressive()
            else -> mood
        }
    }
}

interface WhatCanHappen {
    fun seeHero()
    fun getHit(pointsOfDamage: Int)
    fun timePassed()
}

sealed class Mood {}

class Still : Mood()

class Aggressive : Mood()

class Retreating : Mood()

class Dead : Mood()