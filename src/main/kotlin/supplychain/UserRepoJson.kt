package supplychain

//import java.io.File
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

class UserRepoJson: UserRepo {

    private val data: String = """
        [
          {
            "userId": "ZU123",
            "companyId": "ZC789"
          }
        ]
    """.trimIndent()
    private val mapper = jacksonObjectMapper().registerKotlinModule()

    override fun fetchUserCompanyId(userId: String): String? {
        val jsonTextList: List<UserModel> = mapper.readValue<List<UserModel>>(data)
        val user: List<UserModel> = jsonTextList.filter {
            it.userId == userId
        }
        if (user.isEmpty()) {
            return null
        }
        return user.first().companyId
    }

//    override fun fetchUserCompanyId(userId: String): String {
//        val mapper = jacksonObjectMapper()
//        mapper.registerKotlinModule()
//        val jsonString: String = this::class.java.getResource("/users.json")?.readText() ?: "[]"
//        val jsonTextList: List<UserModel> = mapper.readValue<List<UserModel>>(jsonString)
//        val user: List<UserModel> = jsonTextList.filter {
//            it.userId == userId
//        }
//        if (user.isEmpty()) {
//            return "null"
//        } else {
//            return user.first().companyId
//        }
//    }
}