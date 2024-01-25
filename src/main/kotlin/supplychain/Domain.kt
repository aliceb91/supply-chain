package supplychain

class Domain(private val userRepo: UserRepo, private val supplyChainRepo: SupplyChainRepo) {

    fun fetchDirectSuppliers(userId: String): Map<String, *> {
        val companyId = userRepo.fetchUserCompanyId(userId)
            ?: return mapOf(
                "companyId" to "notFound"
            )
        val directSuppliers = supplyChainRepo.fetchDirectSupplyChain(companyId)
        return mapOf(
            "companyId" to companyId,
            "directSuppliers" to directSuppliers
        )
    }

    fun fetchDirectSupplier(userId: String, targetCompanyId: String): Map<String, *> {
        val companyId = userRepo.fetchUserCompanyId(userId)
            ?: return mapOf(
                "companyId" to "notFound"
            )
        val directSuppliers = supplyChainRepo.fetchDirectSupplyChain(companyId)
        if (targetCompanyId !in directSuppliers) {
            return mapOf(
                "companyId" to "notFound"
            )
        }
        return supplyChainRepo.fetchDirectSupplierById(targetCompanyId)
    }

    fun addDirectSupplierToChain(userId: String, targetCompanyId: String): Map<String, *> {
        val companyId = userRepo.fetchUserCompanyId(userId)
            ?: return mapOf(
                "companyId" to "notFound"
            )
        val directSuppliers = supplyChainRepo.fetchDirectSupplyChain(companyId)
        if (targetCompanyId in directSuppliers) {
            return mapOf(
                "companyId" to "conflict"
            )
        }
        return supplyChainRepo.addDirectSupplierById(companyId, targetCompanyId)
    }
}