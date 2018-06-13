package chapter10


fun main(vararg args: String) {

    setCapacity(1)
   // setCapacity(-1)

  //  printNameLength(Profile(null, "Smith"))

    val client = HttpClient()

    client.body = ""
    client.postRequest()
}

fun setCapacity(cap: Int) {
    require(cap > 0)
}

fun printNameLength(p: Profile) {
    //require(p.firstName != null)
    requireNotNull(p.firstName)
}

private class HttpClient {
    var body: String? = null
    var url: String = ""

    fun postRequest() {
        check(body != null) {
            "Body must be set in POST requests"
        }
    }

    fun getRequest() {
        // This one is fine without body
    }
}