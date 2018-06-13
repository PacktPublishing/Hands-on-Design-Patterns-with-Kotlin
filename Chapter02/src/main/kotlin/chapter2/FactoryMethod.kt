package chapter2

fun main(args: Array<String>) {

    val animalTypes = listOf("dog" to "bulldog",
            "dog" to "beagle",
            "cat" to "persian",
            "cat" to "russian blue",
            "cat" to "siamese")

    val factory = AnimalFactory()
    for ((type, breed) in animalTypes) {
        val c = factory.createAnimal(type, breed)
        println(c)
    }
}



class AnimalFactory {
    var counter = 0
    private val dogFactory = DogFactory()
    private val catFactory = CatFactory()

    fun createAnimal(animalType: String, animalBreed: String) : Animal {
        return when(animalType.trim().toLowerCase()) {
            "cat" -> catFactory.createCat(animalBreed, ++counter)
            "dog" -> dogFactory.createDog(animalBreed, ++counter)
            else -> throw RuntimeException("Unknown animal $animalType")
        }
    }
}

class CatFactory {
    fun createCat(animalBreed: String, i: Int): Animal {
        return Cat(i)
    }

}

class DogFactory {
    fun createDog(breed: String, id: Int) = when(breed.trim().toLowerCase()) {
        "beagle" -> Beagle(id)
        "bulldog" -> Bulldog(id)
        else -> throw RuntimeException("Unknown dog breed $breed")
    }
}

class Beagle(id: Int) : Dog(id)

class Bulldog(id: Int) : Dog(id)

interface Animal {
    val id : Int
    // Same as before
}

class Cat(override val id: Int) : Animal {
    // Same as before
}

open class Dog(override val id: Int) : Animal {
    // Same as before
}