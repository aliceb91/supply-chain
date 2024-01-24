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

    fun fetchDirectSupplier(userId: String, targetCompanyId: String): Map<String, *> {
        val companyId = userRepo.fetchUserCompanyId(userId)
        if (companyId == "null") {
            return mapOf(
                "companyId" to "null"
            )
        }
        val directSuppliers = supplyChainRepo.fetchDirectSupplyChain(companyId)
        if (targetCompanyId !in directSuppliers) {
            return mapOf(
                "companyId" to "null"
            )
        }
        return supplyChainRepo.fetchDirectSupplierById(targetCompanyId)
    }
}