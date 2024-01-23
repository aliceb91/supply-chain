package supplychain

import com.fasterxml.jackson.databind.JsonNode
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer
import org.http4k.core.Body
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.with
import org.http4k.format.Jackson.asJsonObject
import org.http4k.format.Jackson.asJsonValue
import org.http4k.format.Jackson.asPrettyJsonString
import org.http4k.format.Jackson.json

val app: HttpHandler = routes(
    "/suppliers" bind GET to { req: Request ->
        val supplyChainRepo: SupplyChainRepo = SupplyChainRepoJson()
        val userRepo: UserRepo = UserRepoJson()
        val domain = Domain(userRepo, supplyChainRepo)
        val userId = req.header("userId")
        if (userId == null) {
            Response(BAD_REQUEST)
        } else {
            val data: Map<String, *> = domain.fetchDirectSuppliers(userId)
            if (data["companyId"] == "null") {
                Response(NOT_FOUND)
            } else {
                Response(OK).body(data.asJsonObject().toString())
            }
        }
    }
)

// Enable testServer() if client testing required

//fun testServer(): Http4kServer {
//
//    val server = app.asServer(SunHttp(9000))
//
//    println("Server started on " + server.port())
//
//    return server
//}

fun main() {

    val printingApp: HttpHandler = PrintRequest().then(app)

    val server = printingApp.asServer(SunHttp(9000)).start()

    println("Server started on " + server.port())
}