package supplychain

interface SupplyChainRepo {

    fun fetchDirectSuppliers(companyId: String): List<String>

    fun fetchDirectSupplierById(targetCompanyId: String): CompanyModel?

    fun addDirectSupplierById(companyId: String, targetCompanyId: String): CompanyModel?
}