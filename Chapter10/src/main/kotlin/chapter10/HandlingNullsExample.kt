package chapter10

import java.util.*

fun main(vararg args: String) {

    val stringOrNull: String? = if (Random().nextBoolean()) "String" else null

    if (stringOrNull != null) {
        println(stringOrNull.length)
    }

    val alwaysLength = stringOrNull?.length ?: 0

    println(alwaysLength)

    val json: Json? = Json(Profile(null, null))

    println(json?.User?.firstName?.length)

    println(json?.let {
        it.User?.let {
            it.firstName?.length
        }
    })

    println(json?.run {
        User?.run {
            firstName?.length
        }
    })

    println(json!!.User!!.firstName!!.length)
}

data class Json(
        val User: Profile?
)

data class Profile(val firstName: String?,
                   val lastName: String?)