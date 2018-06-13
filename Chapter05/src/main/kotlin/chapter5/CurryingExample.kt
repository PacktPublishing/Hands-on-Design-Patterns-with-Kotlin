package chapter5



fun main(args: Array<String>) {

    println(subtract(4)(5))

}

fun subtract(x: Int, y: Int): Int {
    return x - y
}


/*fun subtract(x: Int): (Int) -> Int {
    return fun(y: Int): Int {
        return x - y
    }
}*/

fun subtract(x: Int) = {y: Int -> x - y }

//fun subtract(x: Int) = fun(y: Int) = x - y