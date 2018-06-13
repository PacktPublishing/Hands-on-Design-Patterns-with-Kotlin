package chapter10


fun main(vararg args: String) {

    // Inline function call
    makesSense {
        "Inlining"
    }

    // Just same code
    println("Before")
    println("Inlining")
    println("After")
}

inline fun doesntMakeSense(something: String) {
    println(something)
}

inline fun makesSense(block: () -> String) {
    println("Before")
    println(block())
    println("After")
}
