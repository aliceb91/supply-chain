package supplychain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DomainTest {

    @Test
    fun `given a userId, it returns a map of the companyId and direct suppliers`() {
        class MockUserRepo: UserRepo {
            override fun fetchUserCompanyId(userId: String): String {
                return "ZC789"
            }
        }
        class MockSupplyChainRepo: SupplyChainRepo {
            override fun fetchDirectSupplyChain(companyId: String): List<String> {
                return listOf("ZS456")
            }
        }
        val mockUserRepo = MockUserRepo()
        val mockSupplyChainRepo = MockSupplyChainRepo()
        val underTest = Domain(mockUserRepo, mockSupplyChainRepo)
        val expected: Map<String, *> = mapOf(
            "companyId" to "ZC789",
            "directSuppliers" to listOf("ZS456")
        )
        val result: Map<String, *> = underTest.fetchDirectSuppliers("ZU123")
        assertEquals(expected, result)
    }

    @Test
    fun `given an invalid userId it returns a map with a single null entry for companyId`() {
        class MockUserRepo: UserRepo {
            override fun fetchUserCompanyId(userId: String): String {
                return "null"
            }
        }
        class MockSupplyChainRepo: SupplyChainRepo {
            override fun fetchDirectSupplyChain(companyId: String): List<String> {
                return listOf()
            }
        }
    }
}