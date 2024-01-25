package supplychain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested

class DomainTest {

    @Nested
    inner class DirectSuppliers {
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

                override fun fetchDirectSupplierById(companyId: String): Map<String, *> {
                    TODO()
                }

                override fun addDirectSupplierById(companyId: String, targetCompanyId: String): Map<String, *> {
                    TODO()
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

                override fun fetchDirectSupplierById(companyId: String): Map<String, *> {
                    TODO()
                }

                override fun addDirectSupplierById(companyId: String, targetCompanyId: String): Map<String, *> {
                    TODO()
                }
            }
        }
    }

    @Nested
    inner class DirectSupplier {

        @Test
        fun `given a valid user and supplier Id, it returns the supplier data`() {
            class MockUserRepo: UserRepo {
                override fun fetchUserCompanyId(userId: String): String {
                    return "ZC789"
                }
            }
            class MockSupplyChainRepo: SupplyChainRepo {
                override fun fetchDirectSupplyChain(companyId: String): List<String> {
                    return listOf("ZS456")
                }

                override fun fetchDirectSupplierById(companyId: String): Map<String, *> {
                    return mapOf(
                        "companyId" to "ZS456",
                        "buyers" to listOf<String>(),
                        "suppliers" to listOf<String>()
                    )
                }

                override fun addDirectSupplierById(companyId: String, targetCompanyId: String): Map<String, *> {
                    TODO()
                }
            }
            val mockUserRepo: UserRepo = MockUserRepo()
            val mockSupplyChainRepo: SupplyChainRepo = MockSupplyChainRepo()
            val underTest = Domain(mockUserRepo, mockSupplyChainRepo)
            val expected: Map<String, *> = mapOf(
                "companyId" to "ZS456",
                "buyers" to listOf<String>(),
                "suppliers" to listOf<String>()
            )
            val result: Map<String, *> = underTest.fetchDirectSupplier("ZU123", "ZS456")
            assertEquals(expected, result)
        }

        @Test
        fun `given a companyId that is not a direct supplier, it returns a null map`() {
            class MockUserRepo: UserRepo {
                override fun fetchUserCompanyId(userId: String): String {
                    return "ZC789"
                }
            }
            class MockSupplyChainRepo: SupplyChainRepo {
                override fun fetchDirectSupplyChain(companyId: String): List<String> {
                    return listOf("ZS456")
                }

                override fun fetchDirectSupplierById(companyId: String): Map<String, *> {
                    return mapOf(
                        "companyId" to "ZS456",
                        "buyers" to listOf<String>(),
                        "suppliers" to listOf<String>()
                    )
                }

                override fun addDirectSupplierById(companyId: String, targetCompanyId: String): Map<String, *> {
                    TODO()
                }
            }
            val mockUserRepo: UserRepo = MockUserRepo()
            val mockSupplyChainRepo: SupplyChainRepo = MockSupplyChainRepo()
            val underTest = Domain(mockUserRepo, mockSupplyChainRepo)
            val expected: Map<String, *> = mapOf(
                "companyId" to "null"
            )
            val result: Map<String, *> = underTest.fetchDirectSupplier("ZU123", "ZS654")
            assertEquals(expected, result)
        }

        @Test
        fun `given a userId that does not exist in the system, it returns a null map`() {
            class MockUserRepo: UserRepo {
                override fun fetchUserCompanyId(userId: String): String {
                    return "null"
                }
            }
            class MockSupplyChainRepo: SupplyChainRepo {
                override fun fetchDirectSupplyChain(companyId: String): List<String> {
                    return listOf("ZS456")
                }

                override fun fetchDirectSupplierById(companyId: String): Map<String, *> {
                    return mapOf(
                        "companyId" to "ZS456",
                        "buyers" to listOf<String>(),
                        "suppliers" to listOf<String>()
                    )
                }

                override fun addDirectSupplierById(companyId: String, targetCompanyId: String): Map<String, *> {
                    TODO()
                }
            }
            val mockUserRepo: UserRepo = MockUserRepo()
            val mockSupplyChainRepo: SupplyChainRepo = MockSupplyChainRepo()
            val underTest = Domain(mockUserRepo, mockSupplyChainRepo)
            val expected: Map<String, *> = mapOf(
                "companyId" to "null"
            )
            val result: Map<String, *> = underTest.fetchDirectSupplier("hello", "ZS456")
            assertEquals(expected, result)
        }
    }

    @Nested
    inner class AddDirectSupplierToChain {

        @Test
        fun `given valid parameters, it adds the supplier to the supply chain and returns the company data`() {

            class MockUserRepo: UserRepo {
                override fun fetchUserCompanyId(userId: String): String {
                    return "ZC789"
                }
            }
            class MockSupplyChainRepo: SupplyChainRepo {
                override fun fetchDirectSupplyChain(companyId: String): List<String> {
                    return listOf("ZS456")
                }

                override fun fetchDirectSupplierById(companyId: String): Map<String, *> {
                    return mapOf(
                        "companyId" to "ZS456",
                        "buyers" to listOf<String>(),
                        "suppliers" to listOf<String>()
                    )
                }

                override fun addDirectSupplierById(companyId: String, targetCompanyId: String): Map<String, *> {
                    return mapOf(
                        "companyId" to "ZC789",
                        "buyers" to listOf<String>(),
                        "suppliers" to listOf<String>("ZS456", "ZS654")
                    )
                }
            }
            val mockUserRepo: UserRepo = MockUserRepo()
            val mockSupplyChainRepo: SupplyChainRepo = MockSupplyChainRepo()
            val underTest = Domain(mockUserRepo, mockSupplyChainRepo)
            val expected: Map<String, *> = mapOf(
                "companyId" to "ZC789",
                "buyers" to listOf<String>(),
                "suppliers" to listOf<String>("ZS456", "ZS654")
            )
            val result: Map<String, *> = underTest.addDirectSupplierToChain("ZU123", "ZS654")
            assertEquals(expected, result)
        }

        @Test
        fun `given a targetId that is alrady present in direct suppliers, it returns a conflict map`() {

            class MockUserRepo : UserRepo {
                override fun fetchUserCompanyId(userId: String): String {
                    return "ZC789"
                }
            }

            class MockSupplyChainRepo : SupplyChainRepo {
                override fun fetchDirectSupplyChain(companyId: String): List<String> {
                    return listOf("ZS456")
                }

                override fun fetchDirectSupplierById(companyId: String): Map<String, *> {
                    return mapOf(
                        "companyId" to "ZS456",
                        "buyers" to listOf<String>(),
                        "suppliers" to listOf<String>()
                    )
                }

                override fun addDirectSupplierById(companyId: String, targetCompanyId: String): Map<String, *> {
                    return mapOf(
                        "companyId" to "ZC789",
                        "buyers" to listOf<String>(),
                        "suppliers" to listOf<String>("ZS456", "ZS654")
                    )
                }
            }

            val mockUserRepo: UserRepo = MockUserRepo()
            val mockSupplyChainRepo: SupplyChainRepo = MockSupplyChainRepo()
            val underTest = Domain(mockUserRepo, mockSupplyChainRepo)
            val expected: Map<String, *> = mapOf(
                "companyId" to "conflict"
            )
            val result: Map<String, *> = underTest.addDirectSupplierToChain("ZU123", "ZS456")
            assertEquals(expected, result)
        }

        @Test
        fun `when given the targetId of a company that does not exist in the database, it returns not found`() {
            class MockUserRepo : UserRepo {
                override fun fetchUserCompanyId(userId: String): String {
                    return "ZC789"
                }
            }

            class MockSupplyChainRepo : SupplyChainRepo {
                override fun fetchDirectSupplyChain(companyId: String): List<String> {
                    return listOf("ZS456")
                }

                override fun fetchDirectSupplierById(companyId: String): Map<String, *> {
                    return mapOf(
                        "companyId" to "ZS456",
                        "buyers" to listOf<String>(),
                        "suppliers" to listOf<String>()
                    )
                }

                override fun addDirectSupplierById(companyId: String, targetCompanyId: String): Map<String, *> {
                    return mapOf(
                        "companyId" to "null"
                    )
                }
            }

            val mockUserRepo: UserRepo = MockUserRepo()
            val mockSupplyChainRepo: SupplyChainRepo = MockSupplyChainRepo()
            val underTest = Domain(mockUserRepo, mockSupplyChainRepo)
            val expected: Map<String, *> = mapOf(
                "companyId" to "null"
            )
            val result: Map<String, *> = underTest.addDirectSupplierToChain("ZU123", "ZS987")
            assertEquals(expected, result)
        }
    }
}