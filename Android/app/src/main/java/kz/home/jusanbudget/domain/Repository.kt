package kz.home.jusanbudget.domain

import kz.home.jusanbudget.data.AuthenticationRequest

interface Repository {

    suspend fun authUser(login: AuthenticationRequest)

    suspend fun registerUser(login: AuthenticationRequest)

    suspend fun getBonus(): String
}