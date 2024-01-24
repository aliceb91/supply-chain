package supplychain

import com.fasterxml.jackson.databind.JsonNode
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.format.Jackson.asJsonObject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ApiTest {

    @Nested
    inner class DirectSuppliers {

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

    @Nested
    inner class DirectSupplier {

        @Test
        fun `given a valid userId and target companyId, it returns the requested direct supplier`() {
            val expected: String = mapOf(
                "companyId" to "ZS456",
                "buyers" to listOf<String>(),
                "suppliers" to listOf<String>()
            ).asJsonObject().toString()
            val result: String = app(Request(GET, "/target_supplier?type=direct")
                .header("userId", "ZU123")
                .header("targetCompanyId", "ZS456")
            ).body.toString()
            assertEquals(expected, result)
        }

        @Test
        fun `given a valid userId and an invalid target companyId it returns not found`() {
            val expected: Response = Response(NOT_FOUND)
            val result: Response = app(Request(GET, "/target_supplier?type=direct")
                .header("userId", "ZU123")
                .header("targetCompanyId", "ZS654")
            )
            assertEquals(expected, result)
        }

        @Test
        fun `given a valid userId but no target companyId it returns bad request`() {
            val expected: Response = Response(BAD_REQUEST)
            val result: Response = app(Request(GET, "/target_supplier?type=direct")
                .header("userId", "ZU123")
            )
            assertEquals(expected, result)
        }

        @Test
        fun `given a valid companyId but no userId, it returns bad request`() {
            val expected: Response = Response(BAD_REQUEST)
            val result: Response = app(Request(GET, "target_supplier?type=direct")
                .header("targetCompanyId", "ZS654")
            )
            assertEquals(expected, result)
        }
    }
}