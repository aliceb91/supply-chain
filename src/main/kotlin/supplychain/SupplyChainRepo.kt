package supplychain

interface SupplyChainRepo {

    fun fetchDirectSupplyChain(companyId: String): List<String>

    fun fetchDirectSupplierById(targetCompanyId: String): Map<String, *>

    fun addDirectSupplierById(companyId: String, targetCompanyId: String): Map<String, *>
}