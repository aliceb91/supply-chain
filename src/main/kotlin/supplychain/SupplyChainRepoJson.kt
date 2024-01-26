package supplychain

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
//import java.io.File
//import java.nio.file.Paths

class SupplyChainRepoJson: SupplyChainRepo {

    private var data: String = """
       [
         {
           "companyId": "ZC789",
           "buyers": [],
           "suppliers": ["ZS456"]
         },
         {
            "companyId": "ZS456",
            "buyers": [],
            "suppliers": []
         },
         {
            "companyId": "ZS654",
            "buyers": [],
            "suppliers": []
         }
       ]
    """.trimIndent()
    private val mapper = jacksonObjectMapper().registerKotlinModule()

    override fun fetchDirectSuppliers(companyId: String): List<String> {
        val jsonTextList: List<CompanyModel> = mapper.readValue<List<CompanyModel>>(data)
        val company = jsonTextList.first {
            it.companyId == companyId
        }
        return company.suppliers
    }

    override fun fetchDirectSupplierById(targetCompanyId: String): Map<String, *> {
        val jsonTextList: List<CompanyModel> = mapper.readValue<List<CompanyModel>>(data)
        val company = jsonTextList.first {
            it.companyId == targetCompanyId
        }
        return mapOf(
            "companyId" to company.companyId,
            "buyers" to company.buyers,
            "suppliers" to company.suppliers
        )
    }

    override fun addDirectSupplierById(companyId: String, targetCompanyId: String): Map<String, *> {
        val jsonTextList: List<CompanyModel> = mapper.readValue<List<CompanyModel>>(data)
        val targetCompany: List<CompanyModel> = jsonTextList.filter {
            it.companyId == targetCompanyId
        }
        if (targetCompany.isEmpty()) {
            return mapOf(
                "companyId" to "notFound"
            )
        }
        val updatedList: List<CompanyModel> = jsonTextList.map {
            if (it.companyId == companyId) {
                it.suppliers.add(targetCompanyId)
            }
            it
        }
        data = mapper.writeValueAsString(updatedList)
        val newJsonList: List<CompanyModel> = mapper.readValue<List<CompanyModel>>(data)
        val company: CompanyModel = newJsonList.first {
            it.companyId == companyId
        }
        return mapOf(
            "companyId" to company.companyId,
            "buyers" to company.buyers,
            "suppliers" to company.suppliers
        )
    }

//    override fun fetchDirectSupplyChain(companyId: String): List<String> {
//        val mapper = jacksonObjectMapper()
//        mapper.registerKotlinModule()
//        val jsonString: String = this::class.java.getResource("/companies.json")?.readText() ?: "[]"
//        val jsonTextList: List<CompanyModel> = mapper.readValue<List<CompanyModel>>(jsonString)
//        val company = jsonTextList.filter {
//            it.companyId == companyId
//        }.first()
//        return company.suppliers
//    }
}