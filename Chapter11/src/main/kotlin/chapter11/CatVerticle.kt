package chapter11

import io.vertx.core.json.JsonObject
import io.vertx.kotlin.core.json.get
import io.vertx.kotlin.coroutines.CoroutineVerticle
import kotlinx.coroutines.experimental.launch

const val CATS = "cats:get"
private const val QUERY_ALL = """select * from cats"""
class CatVerticle : CoroutineVerticle() {

    private val QUERY_WITH_ID = """select * from cats
                     where id = ?::integer""".trimIndent()

    override suspend fun start() {
        val db = getDbClient()
        vertx.eventBus().consumer<JsonObject>(CATS) { req ->
            try {
                val body = req.body()
                val id: Int? = body["id"]
                val result = if (id != null) {
                    db.query(QUERY_WITH_ID, id)
                } else {
                    db.query(QUERY_ALL)
                }
                launch {
                    req.reply(result.await())
                }
            }
            catch (e: Exception) {
                req.fail(0, e.message)
            }
        }
    }
}