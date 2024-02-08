package supplychain

data class SupplyChainModel(
    var companyId: String? = null,
    var buyers: List<String>? = null,
    var suppliers: List<String>? = null
)