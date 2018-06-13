package chapter2

data class Mail(val to: String,
                var title: String = "",
                var _message: String = "",
                val cc: List<String> = listOf(),
                val bcc: List<String> = listOf(),
                val attachments: List<java.io.File> = listOf()) {

    fun message(message: String) = apply {
        _message = message
    }
}

fun main(args: Array<String>) {


    val mail = Mail("hello").message("How are you?")
}