package chapter9

import kotlinx.coroutines.experimental.*
import java.util.*
import java.util.concurrent.CountDownLatch

fun main(vararg args: String) = runBlocking {

    //take1()
   //take2()
 //   take3()
    favorites()
}

private fun take3() = runBlocking {
    val character = FavoriteCharacter(getName().await(), getCatchphrase().await(), getRepeats().await())

    with(character) {
        println("$name says: ${catchphrase.repeat(repeats)}")
    }

    val (name, catchphrase, repeats) = character
    println("$name says: ${catchphrase.repeat(repeats)}")
}

private fun take1() {
    val latch = CountDownLatch(3)

    var name: String? = null
    launch {
        delay(Random().nextInt(100))
        println("Got name")
        name = "Inigo Montoya"
        latch.countDown()
    }

    var catchphrase = ""
    launch {
        delay(Random().nextInt(100))
        println("Got catchphrase")
        catchphrase = "Hello. My name is Inigo Montoya. You killed my father. Prepare to die."
        latch.countDown()
    }

    var repeats = 0
    launch {
        delay(Random().nextInt(100))
        println("Got repeats")
        repeats = 6
        latch.countDown()
    }

    latch.await()

    println("${name} says: ${catchphrase.repeat(repeats)}")
}

data class FavoriteCharacter(val name: String, val catchphrase: String, val repeats: Int)

private fun take2() = runBlocking {
    val name = getName()
    val catchphrase = getCatchphrase()
    val repeats = getRepeats()

    println("${name.await()} says: ${catchphrase.await().repeat(repeats.await())}")
}

private fun getName() = async {
    delay(Random().nextInt(100))
    println("Got name")
    "Inigo Montoya"
}

private fun getCatchphrase(): Deferred<String> {
    return async {
        delay(Random().nextInt(100))
        println("Got catchphrase")
        "Hello. My name is Inigo Montoya. You killed my father. Prepare to die."
    }
}

private fun getRepeats() = async {
    delay(Random().nextInt(100))
    println("Got repeats")
    6
}

object Michael {
    fun getFavoriteCharacter() = async {
        // Doesn't like to think much
        delay(Random().nextInt(10))
        FavoriteCharacter("Terminator", "Hasta la vista, baby", 1)
    }
}

object Jake {
    fun getFavoriteCharacter() = async {
        // Rather thoughtful barista
        delay(Random().nextInt(100) + 10)
        FavoriteCharacter("Don Vito Corleone", "I'm going to make him an offer he can't refuse", 1)
    }
}

object Me {
    fun getFavoriteCharacter() = async {
        // I already prepared the answer!
        FavoriteCharacter("Inigo Montoya", "Hello, my name is...", 6)
    }
}

fun favorites() = runBlocking {
    val favoriteCharacters = listOf(Me.getFavoriteCharacter().await(),
            Michael.getFavoriteCharacter().await(),
            Jake.getFavoriteCharacter().await())

    println(favoriteCharacters)
}