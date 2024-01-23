package supplychain

class Domain(val userRepo: UserRepo, val supplyChainRepo: SupplyChainRepo) {

    fun fetchDirectSuppliers(userId: String): Map<String, *> {
        val companyId = userRepo.fetchUserCompanyId(userId)
        if (companyId == "null") {
            return mapOf(
                "companyId" to "null"
            )
        } else {
            val directSuppliers = supplyChainRepo.fetchDirectSupplyChain(companyId)
            return mapOf(
                "companyId" to companyId,
                "directSuppliers" to directSuppliers
            )
        }
    }
}