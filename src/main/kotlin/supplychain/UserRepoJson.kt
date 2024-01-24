package supplychain

import java.io.File
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

class UserRepoJson(): UserRepo {

    val data: String = """
        [
          {
            "userId": "ZU123",
            "companyId": "ZC789"
          }
        ]
    """.trimIndent()

    override fun fetchUserCompanyId(userId: String): String {
        val mapper = jacksonObjectMapper()
        mapper.registerKotlinModule()
        val jsonTextList: List<UserModel> = mapper.readValue<List<UserModel>>(data)
        val user: List<UserModel> = jsonTextList.filter {
            it.userId == userId
        }
        if (user.isEmpty()) {
            return "null"
        } else {
            return user.first().companyId
        }
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