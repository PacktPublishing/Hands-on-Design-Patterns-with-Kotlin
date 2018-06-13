package chapter4


fun main(args: Array<String>) {

    val rangers = Squad("Josh", "Ewan", "Tom")
    val deltaForce = Squad("Sam", "Eric", "William")
    val blackHawk = Platoon(rangers, deltaForce)

    printAll(deltaForce.iterator())
    printAll(deltaForce.reverseIterator())
}

fun <T> printAll(iter: Iterator<T>) {
    while (iter.hasNext()) {
        println(iter.next())
    }
}

class Squad(private val infantryUnits: MutableList<InfantryUnit> = mutableListOf()) {
    val commander = Sergeant()

    constructor(s0: String, s1: String, s2: String) : this() {
        infantryUnits.add(object : InfantryUnit {})
        infantryUnits.add(object : InfantryUnit {})
        infantryUnits.add(object : InfantryUnit {})
    }

    operator fun iterator() = object: Iterator<InfantryUnit> {
        var i = 0
        override fun hasNext(): Boolean {
            return i < infantryUnits.size + 1
        }

        override fun next() =
            when (i) {
                0 -> commander
                else -> infantryUnits[i - 1]
            }.also { i++ }
    }

    fun reverseIterator() = object: Iterator<InfantryUnit> {
        var i = 0
        override fun hasNext(): Boolean {
            return i < infantryUnits.size + 1
        }

        override fun next() =
            when (i) {
                infantryUnits.size -> commander
                else -> infantryUnits[infantryUnits.size - i - 1]
            }.also { i++ }
    }
}

class Platoon(val squads: MutableList<Squad> = mutableListOf()) {
    val commander = Lieutenant()

    constructor(squads: Squad, deltaForce: Squad) : this()

    operator fun iterator() = object: Iterator<InfantryUnit> {
        var i = 0

        override fun hasNext(): Boolean {
            return false
        }

        override fun next(): InfantryUnit {
            return commander
        }
    }
}

interface InfantryUnit

class Sergeant: InfantryUnit

class Lieutenant: InfantryUnit