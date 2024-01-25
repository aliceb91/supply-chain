package supplychain

interface UserRepo {

    fun fetchUserCompanyId(userId: String): String?
}