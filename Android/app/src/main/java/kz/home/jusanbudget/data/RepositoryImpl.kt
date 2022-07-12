package kz.home.jusanbudget.data

import kz.home.jusanbudget.domain.Repository
import retrofit2.Retrofit

const val login = "test"
const val password = "123"

class RepositoryImpl(retrofit: Retrofit) : Repository {
    private val apiService = retrofit.create(ApiService::class.java)
    override suspend fun authUser(login: AuthenticationRequest): String {
        //val m = apiService.authUser(login)
        return ""
    }

    override suspend fun getBonus(): String {
        return apiService.getBonus()
    }
}