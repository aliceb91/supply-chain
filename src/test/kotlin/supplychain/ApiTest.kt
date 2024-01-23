package supplychain

import com.fasterxml.jackson.databind.JsonNode
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.format.Jackson.asJsonObject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ApiTest {

    @Test
    fun `given a userId, it returns a list of direct suppliers for that users company`() {
        val expected: String = mapOf(
            "companyId" to "ZC789",
            "directSuppliers" to listOf("ZS456")
        ).asJsonObject().toString()
        val result = app(Request(GET, "/suppliers?type=direct")
            .header("userId", "ZU123")
        ).body.toString()
        assertEquals(expected, result)
    }

    @Test
    fun `given no userId provided, it returns a bad request code`() {
        val expected: Response = Response(BAD_REQUEST)
        val result: Response = app(Request(GET, "/suppliers?type=direct"))
        assertEquals(expected, result)
    }

    @Test
    fun `given no supplier type is provided, it defaults to direct suppliers`() {
        val expected: String = mapOf(
            "companyId" to "ZC789",
            "directSuppliers" to listOf("ZS456")
        ).asJsonObject().toString()
        val result = app(Request(GET, "/suppliers")
            .header("userId", "ZU123")
        ).body.toString()
        assertEquals(expected, result)
    }

    @Test
    fun `given an invalid userId, it returns a not found code`() {
        val expected: Response = Response(NOT_FOUND)
        val result: Response = app(Request(GET, "/suppliers?type=direct")
            .header("userId", "hello")
        )
        assertEquals(expected,result)
    }
}