package chapter11

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.vertx.core.Vertx
import io.vertx.core.buffer.Buffer
import io.vertx.ext.web.client.HttpResponse
import io.vertx.ext.web.client.WebClient
import kotlinx.coroutines.experimental.CompletableDeferred
import kotlinx.coroutines.experimental.runBlocking
import org.testng.Assert.*
import org.testng.annotations.*


internal class ServerVerticleTest {
    // Usually one instance of VertX is more than enough
    val vertx = Vertx.vertx()

    @BeforeClass
    fun setUp() {
        // You want to start your server once
        startServer()
    }

    private fun startServer() {
        val d1 = CompletableDeferred<String>()
        vertx.deployVerticle(ServerVerticle(), {
            d1.complete("OK")
        })
        vertx.deployVerticle(CatVerticle())
        runBlocking {
            println("Server started")
            d1.await()
        }
    }

    @AfterClass
    fun tearDown() {
        // And you want to stop your server once
        vertx.close()
    }

    @Test
    fun `Tests that alive works`() {
        val response = get("/alive")
        assertEquals(response.statusCode(), 200)

        val body = response.asJson()
        assertEquals(body["alive"].booleanValue(), true)
    }

    @Test
    fun `Make sure cat can be created`() {
        val response = post("/api/v1/cats",
                """
                {
                    "name": "Binky",
                    "age": 5
                }
                """)

        assertEquals(response.statusCode(), 201)

        val body = response.asJson()

        assertNotNull(body["id"])
        assertEquals(body["name"].textValue(), "Binky")
        assertEquals(body["age"].intValue(), 5)
    }

    @Test
    fun `Make sure that all cats are returned`() {
        val response = get("/api/v1/cats")
        assertEquals(response.statusCode(), 200)

        val body = response.asJson()

        assertTrue(body.size() > 0)
    }

    private fun get(path: String): HttpResponse<Buffer> {
        val d1 = CompletableDeferred<HttpResponse<Buffer>>()

        val client = WebClient.create(vertx)
        client.get(8080, "localhost", path).send {
            d1.complete(it.result())
        }

        return runBlocking {
            d1.await()
        }
    }

    private fun post(path: String, body: String = ""): HttpResponse<Buffer> {
        val d1 = CompletableDeferred<HttpResponse<Buffer>>()

        val client = WebClient.create(vertx)
        client.post(8080, "localhost", path).sendBuffer(Buffer.buffer(body), { res ->
            d1.complete(res.result())
        })

        return runBlocking {
            d1.await()
        }
    }
}



private fun <T> HttpResponse<T>.asJson(): JsonNode {
    return this.bodyAsBuffer().asJson()
}

private fun Buffer.asJson(): JsonNode {
    return ObjectMapper().readTree(this.toString())
}