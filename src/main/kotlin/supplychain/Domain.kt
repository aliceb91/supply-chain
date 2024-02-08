package supplychain

class Domain(private val userRepo: UserRepo, private val supplyChainRepo: SupplyChainRepo) {

    fun fetchDirectSuppliers(userId: String): SupplyChainModel {
        val model = SupplyChainModel()
        val companyId: String? = userRepo.fetchUserCompanyId(userId)
        if (companyId == null) {
            return model
        }
        val directSuppliers: List<String>? = supplyChainRepo.fetchDirectSuppliers(companyId)
        model.companyId = companyId
        model.suppliers = directSuppliers
        return model
    }

    fun fetchDirectSupplier(userId: String, targetCompanyId: String): SupplyChainModel {
        val model = SupplyChainModel()
        val companyId = userRepo.fetchUserCompanyId(userId)
        if (companyId == null) {
            return model
        }
        val directSuppliers = supplyChainRepo.fetchDirectSuppliers(companyId)
        if (directSuppliers.contains(targetCompanyId) == false) {
            return model
        }
        val targetDirectSupplier = supplyChainRepo.fetchDirectSupplierById(targetCompanyId)
        if (targetDirectSupplier == null) {
            return model
        }
        model.companyId = targetDirectSupplier.companyId
        model.buyers = targetDirectSupplier.buyers
        model.suppliers = targetDirectSupplier.suppliers
        return model
    }

    fun addDirectSupplierToChain(userId: String, targetCompanyId: String): SupplyChainModel {
        val model = SupplyChainModel()
        val companyId = userRepo.fetchUserCompanyId(userId)
        if (companyId === null) {
            return model
        }
        val directSuppliers = supplyChainRepo.fetchDirectSuppliers(companyId)
        if (targetCompanyId in directSuppliers) {
            model.companyId = "conflict"
            return model
        }
        val updatedCompany = supplyChainRepo.addDirectSupplierById(companyId, targetCompanyId)
        if (updatedCompany === null) {
            return model
        }
        model.companyId = updatedCompany.companyId
        model.buyers = updatedCompany.buyers
        model.suppliers = updatedCompany.suppliers
        return model
    }
}