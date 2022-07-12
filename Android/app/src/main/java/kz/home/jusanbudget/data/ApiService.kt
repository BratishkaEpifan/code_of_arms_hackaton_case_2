package kz.home.jusanbudget.data

import retrofit2.http.*

interface ApiService {
    @POST("auth")
    suspend fun authUser(@Body login: AuthenticationRequest): String

@GET("user/get-bonus")
suspend fun getBonus(
    //@Path("user") user: String
//@Header("token") token: String
): String

}