package chapter10

fun main(vararg args: String) {

    val agentJavaWay = JamesBond()
    agentJavaWay.name = "Sean Connery"
    agentJavaWay.movie = "Dr. No"

    val agent = JamesBond().apply {
        this.name = "Sean Connery"
        this.movie = "Dr. No"
    }

    println(agent.name)
}

class JamesBond {
    lateinit var name: String
    lateinit var movie: String
    lateinit var alsoStarring: String
}