package chapter4.not


fun handleRequest(r: Request) {
    // Validate
    if (r.email.isEmpty() || r.question.isEmpty()) {
        return
    }

    // Authenticate
    // Make sure that you know whos is this user
    if (r.email.isKnownEmail()) {
        return
    }

    // Authorize
    // Requests from juniors are automatically ignored by architects
    if (r.email.isJuniorDeveloper()) {
        return
    }

    println("I don't know. Did you check StackOverflow?")
}

private fun String.isJuniorDeveloper(): Boolean {
    return false
}

private fun String.isKnownEmail(): Boolean {
    return true
}


data class Request(val email: String, val question: String)