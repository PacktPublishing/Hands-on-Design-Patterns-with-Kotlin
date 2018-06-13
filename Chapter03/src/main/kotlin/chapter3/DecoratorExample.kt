package chapter3


fun main(args : Array<String>) {
    val sadHappy = SadMap(HappyMap<String, String>())
    sadHappy["one"] = "one"
    sadHappy["two"] = "two"
    sadHappy["two"] = "three"
    sadHappy["a"] = "b"
    sadHappy.remove("a")

    println(sadHappy is SadMap<*, *>)
    println(sadHappy is HappyMap<*, *>)
    println(sadHappy is MutableMap<*, *>)
}

/*class HappyMap<K, V>: HashMap<K, V>() {
    override fun put(key: K, value: V): V? {
        return super.put(key, value).apply {
            this?.let {
                println("Yay! $key")
            }
        }
    }
}*/

class SadMap<K, V>(private val map : MutableMap<K, V> = mutableMapOf()) : MutableMap<K, V> by map {
    override fun remove(key: K): V? {
        println("Okay...")
        return map.remove(key)
    }
}

class HappyMap<K, V>(private val map : MutableMap<K, V> = mutableMapOf()) : MutableMap<K, V> by map{

    override fun put(key: K, value: V): V? {
        return map.put(key, value).apply {
            if (this != null) {
                println("Yay! $key")
            }
        }
    }
}
