package chapter4

fun main(args: Array<String>) {
    val sql = select("name, age") {
                            from("users") {
                            where("age > 25")
                        }
                     }

    println(sql) // "SELECT name, age FROM users WHERE age > 25"
}

fun select(columns: String, from: SelectClause.()->Unit): SelectClause {
    from(SelectClause(columns))
    return SelectClause(columns).apply(from)
}


class SelectClause(private val columns: String) {

    private lateinit var from : FromClause
    fun from(table: String, where: FromClause.()->Unit): FromClause {
        this.from = FromClause(table)

        return this.from.apply(where)
    }

    override fun toString(): String {
        return "SELECT $columns ${this.from}"
    }
}

class FromClause(private val table: String) {
    private lateinit var where: WhereClause

    fun where(conditions: String) = this.apply {
        where = WhereClause(conditions)
    }

    override fun toString(): String {
        return "FROM $table ${this.where}"
    }
}

class WhereClause(private val conditions: String) {
    override fun toString(): String {
        return "WHERE $conditions"
    }
}
