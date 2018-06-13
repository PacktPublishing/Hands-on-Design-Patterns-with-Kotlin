package chapter4.chainOfResponsibility



typealias Handler = (request: Request) -> Response

fun main(args: Array<String>) {

    val req = Request("developer@company.com", "Why do we need Software Architects?")

    val chain = basicValidation(authentication(finalResponse()))

    val res = chain(req)

    println(res)
}


val basicValidation = fun(next: Handler): Handler {
    return fun(request: Request): Response {
        if (request.email.isEmpty() || request.question.isEmpty()) {
            throw IllegalArgumentException()
        }
        return next(request)
    }
}

val authentication = fun(next: Handler) =
    fun(request: Request): Response {
        if (!request.email.isKnownEmail()) {
            throw IllegalArgumentException()
        }
        return next(request)
    }

val finalResponse = fun() = fun(_: Request) = Response("Try git-blame.")


private fun String.isKnownEmail(): Boolean {
    return true
}


data class Request(val email: String, val question: String)

data class Response(val answer: String)


