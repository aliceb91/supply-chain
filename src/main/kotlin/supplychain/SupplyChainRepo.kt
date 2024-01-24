package supplychain

interface SupplyChainRepo {

    fun fetchDirectSupplyChain(companyId: String): List<String>

    fun fetchDirectSupplierById(companyId: String): Map<String, *>
}