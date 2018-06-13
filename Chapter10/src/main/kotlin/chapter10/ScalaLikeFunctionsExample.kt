package chapter10

fun main(vararg args: String) {
    println("Say ${hello()()}")
}

fun hello() = {
    "hello"
}

fun helloExpanded(): () -> String {
    return {
        "hello"
    }
}

fun helloExpandedMore(): () -> String {
    return fun(): String {
        return "hello"
    }
}