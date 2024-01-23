package supplychain

interface SupplyChainRepo {

    fun fetchDirectSupplyChain(companyId: String): List<String>
}