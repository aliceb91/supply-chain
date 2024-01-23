package supplychain

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import java.nio.file.Paths

class SupplyChainRepoJson(): SupplyChainRepo {

    override fun fetchDirectSupplyChain(companyId: String): List<String> {
        val mapper = jacksonObjectMapper()
        mapper.registerKotlinModule()
        val jsonString: String = this::class.java.getResource("/companies.json")?.readText() ?: "[]"
        val jsonTextList: List<CompanyModel> = mapper.readValue<List<CompanyModel>>(jsonString)
        val company = jsonTextList.filter {
            it.companyId == companyId
        }.first()
        return company.suppliers
    }
}