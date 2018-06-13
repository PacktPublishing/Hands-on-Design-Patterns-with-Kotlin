package chapter4.chainOfResponsibility.java


fun main(args: Array<String>) {
    val req = Request("developer@company.com",
            "Why do we need Software Architects?")

    val chain = AuthenticationHandler(
                    BasicValidationHandler(
                        FinalResponseHandler()))

    val res = chain.handle(req)

    println(res)
}


class BasicValidationHandler(private val next: Handler) : Handler {
    override fun handle(request: Request): Response {
        if (request.email.isEmpty() || request.question.isEmpty()) {
            throw IllegalArgumentException()
        }

        return next.handle(request)
    }
}

class AuthenticationHandler(private val next: Handler) : Handler {
    override fun handle(request: Request): Response {
        if (!request.email.isKnownEmail()) {
            throw IllegalArgumentException()
        }

        return next.handle(request)
    }
}

class FinalResponseHandler : Handler {
    override fun handle(request: Request) = Response("Read documentation.")
}

private fun String.isJuniorDeveloper(): Boolean {
    return true
}

private fun String.isKnownEmail(): Boolean {
    return true
}

interface Handler {
    fun handle(request: Request): Response
}


data class Request(val email: String, val question: String)

data class Response(val answer: String)