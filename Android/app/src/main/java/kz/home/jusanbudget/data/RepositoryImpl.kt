package kz.home.jusanbudget.data

import kz.home.jusanbudget.domain.Repository
import retrofit2.Retrofit

class RepositoryImpl(retrofit: Retrofit) : Repository {
    private val apiService = retrofit.create(ApiService::class.java)
}