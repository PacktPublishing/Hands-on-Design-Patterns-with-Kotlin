package chapter3

fun main(args : Array<String>) {

    val a = listOf("a")
    val b = listOf("b")
    println(a + b)


    val j1 = Json("""{"a": "b"}""")
    val j2 = Json("""{"c": "d"}""")
    println(j1 + j2)
}
data class Json(val j: String)
operator fun Json.plus(j2: Json): Json {
    val jsonFields = this.j.split(":") + j2.j.split(":")
    val s = (jsonFields).joinToString(":")
    return Json ("""{$s}""")
}
