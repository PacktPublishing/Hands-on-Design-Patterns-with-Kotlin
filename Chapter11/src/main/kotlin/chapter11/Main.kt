package chapter11

import io.vertx.core.AsyncResult
import io.vertx.core.Vertx
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.jdbc.JDBCClient
import io.vertx.ext.web.Route
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.core.json.JsonObject
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.experimental.CompletableDeferred
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.launch


fun main(vararg args: String) {
    val vertx = Vertx.vertx()

    vertx.deployVerticle(ServerVerticle())
    vertx.deployVerticle(CatVerticle())
}

fun Route.asyncHandler(fn : suspend (RoutingContext) -> Unit) {
    handler { ctx ->
        launch(ctx.vertx().dispatcher()) {
            try {
                fn(ctx)
            } catch(e: Exception) {
                ctx.fail(e)
            }
        }
    }
}

fun RoutingContext.respond(responseBody: String = "", status: Int = 200) {
    this.response()
            .setStatusCode(status)
            .end(responseBody)
}

fun CoroutineVerticle.getDbClient(): JDBCClient {
    val postgreSQLClientConfig = JsonObject(
            "url" to "jdbc:postgresql://${Config.Db.host}:5432/${Config.Db.database}",
            "username" to Config.Db.username,
            "password" to Config.Db.password)
    return JDBCClient.createShared(vertx, postgreSQLClientConfig)
}

inline fun <T> AsyncResult<T>.handle(success: AsyncResult<T>.() -> Unit, failure: () -> Unit) {
    if (this.succeeded()) {
        success()
    }
    else {
        this.cause().printStackTrace()
        failure()
    }
}

fun JDBCClient.query(q: String, vararg params: Any): Deferred<JsonObject> {
    val deferred = CompletableDeferred<JsonObject>()
    this.getConnection { conn ->
        conn.handle({
            result().queryWithParams(q, params.toJsonArray(), { res ->
                res.handle({
                    deferred.complete(res.result().toJson())
                }, {
                    deferred.completeExceptionally(res.cause())
                })
            })
        }, {
            deferred.completeExceptionally(conn.cause())
        })
    }

    return deferred
}

private fun <T> Array<T>.toJsonArray(): JsonArray {
    val json = JsonArray()

    for (e in this) {
        json.add(e)
    }

    return json
}