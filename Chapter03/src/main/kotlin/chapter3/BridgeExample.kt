package chapter3

fun main(args: Array<String>) {

    val rifleman = Soldier(Rifle(), RegularLegs())
    val grenadier = Soldier(Grenade(), RegularLegs())
    val upgradedGrenadier = Soldier(GrenadePack(), RegularLegs())
    val upgradedRifleman = Soldier(MachineGun(), RegularLegs())
    val lightRifleman = Soldier(Rifle(), AthleticLegs())
    val lightGrenadier = Soldier(Grenade(), AthleticLegs())
}

interface Infantry {
    fun move(x: Long, y: Long)

    fun attack(x: Long, y: Long)
}

typealias PointsOfDamage = Long
typealias Meters = Int

interface Weapon {
    fun causeDamage(): PointsOfDamage
}



const val GRENADE_DAMAGE : PointsOfDamage = 5L
const val RIFLE_DAMAGE = 3L
const val REGULAR_SPEED : Meters = 1

class Grenade : Weapon {
    override fun causeDamage() = GRENADE_DAMAGE
}

class GrenadePack : Weapon {
    override fun causeDamage() = GRENADE_DAMAGE * 3
}

class Rifle : Weapon {
    override fun causeDamage() = RIFLE_DAMAGE
}

class MachineGun : Weapon {
    override fun causeDamage() = RIFLE_DAMAGE * 2
}

class RegularLegs : Legs {
    override fun move() = REGULAR_SPEED
}

class AthleticLegs : Legs {
    override fun move() = REGULAR_SPEED * 2
}

interface Legs {
    fun move(): Meters
}

class Soldier(private val weapon: Weapon,
              private val legs: Legs) : Infantry {
    override fun attack(x: Long, y: Long) {
        // Find target
        // Shoot
        weapon.causeDamage()
    }

    override fun move(x: Long, y: Long) {
        // Compute direction
        // Move at its own pace
        legs.move()
    }
}
