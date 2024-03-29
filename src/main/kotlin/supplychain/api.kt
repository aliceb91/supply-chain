                                package supplychain

import org.http4k.core.*
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Status.Companion.OK
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.CONFLICT
import org.http4k.format.Jackson.asJsonObject

val app: HttpHandler = routes(
    "/suppliers" bind GET to { req: Request ->
        val supplyChainRepo: SupplyChainRepo = SupplyChainRepoJson()
        val userRepo: UserRepo = UserRepoJson()
        val domain = Domain(userRepo, supplyChainRepo)
        val userId = req.header("userId")
        if (userId == null) {
            Response(BAD_REQUEST)
        } else {
            val data: SupplyChainModel = domain.fetchDirectSuppliers(userId)
            if (data.companyId == null) {
                Response(NOT_FOUND)
            } else {
                val responseData = mapOf(
                    "companyId" to data.companyId,
                    "suppliers" to data.suppliers
                )
                Response(OK).body(responseData.asJsonObject().toString())
            }
        }
    },

    "/target_supplier" bind GET to { req: Request ->
        val supplyChainRepo: SupplyChainRepo = SupplyChainRepoJson()
        val userRepo: UserRepo = UserRepoJson()
        val domain = Domain(userRepo, supplyChainRepo)
        val userId = req.header("userId")
        val targetCompanyId = req.header("targetCompanyId")
        if (userId == null || targetCompanyId == null) {
            Response(BAD_REQUEST)
        } else {
            val data: SupplyChainModel = domain.fetchDirectSupplier(userId, targetCompanyId)
            if (data.companyId == null) {
                Response(NOT_FOUND)
            } else {
                val responseData = mapOf(
                    "companyId" to data.companyId,
                    "buyers" to data.buyers,
                    "suppliers" to data.suppliers
                )
                Response(OK).body(responseData.asJsonObject().toString())
            }
        }
    },

    "/add_direct_supplier" bind POST to { req: Request ->
       val supplyChainRepo: SupplyChainRepo = SupplyChainRepoJson()
        val userRepo: UserRepo = UserRepoJson()
        val domain = Domain(userRepo, supplyChainRepo)
        val userId = req.header("userId")
        val targetCompanyId = req.header("targetCompanyId")
        if (userId == null || targetCompanyId == null) {
            Response(BAD_REQUEST)
        } else {
            val data: SupplyChainModel = domain.addDirectSupplierToChain(userId, targetCompanyId)
            if (data.companyId == "conflict") {
                Response(CONFLICT)
            } else if (data.companyId == null) {
                Response(NOT_FOUND)
            } else {
                val responseData = mapOf(
                    "companyId" to data.companyId,
                    "buyers" to data.buyers,
                    "suppliers" to data.suppliers
                )
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