package chapter11

object Config {
    object Db {
        val username = System.getenv("DATABASE_USERNAME") ?: "postgres"
        val password = System.getenv("DATABASE_PASSWORD") ?: ""
        val database = System.getenv("DATABASE_NAME") ?: "cats_db"
        val host = System.getenv("DATABASE_HOST") ?: ""

        override fun toString(): String {
            return mapOf("username" to username,
                    "password" to password,
                    "database" to database,
                    "host" to host).toString()
        }
    }

    override fun toString(): String {
        return mapOf(
                "Db" to Db
        ).toString()
    }
}