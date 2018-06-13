package chapter3

fun main(args: Array<String>) {

    val miller = Rifleman()
    val caparzo = Rifleman()
    val jackson = Sniper()

    val squad = Squad(miller, caparzo, jackson)

    println(squad.bulletsLeft())
}



class Squad(private val infantryUnits: MutableList<InfantryUnit> = mutableListOf()) : CanCountBullets {
    override fun bulletsLeft(): Int {
        return infantryUnits.sumBy { it.bulletsLeft() }
    }

    constructor(vararg units: InfantryUnit) : this(mutableListOf()) {
        for (u in units) {
            this.infantryUnits.add(u)
        }
    }
}

class Bullet

class Magazine(capacity: Int): CanCountBullets {
    private val bullets = List(capacity) { Bullet() }

    override fun bulletsLeft() = bullets.size
}

interface CanCountBullets {
    fun bulletsLeft(): Int
}

interface InfantryUnit : CanCountBullets

class Rifleman(initialMagazines: Int = 3) : InfantryUnit {
    private val magazines = List(initialMagazines) {
        Magazine(5)
    }

    override fun bulletsLeft(): Int {
        return magazines.sumBy { it.bulletsLeft() }
    }
}

class Sniper(initalBullets: Int = 50) : InfantryUnit {

    private val bullets = List(initalBullets) { Bullet () }

    override fun bulletsLeft() = bullets.size
}