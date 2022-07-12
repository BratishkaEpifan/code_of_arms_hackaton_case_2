package kz.home.jusanbudget.domain

import kz.home.jusanbudget.data.AuthenticationRequest

interface Repository {

    suspend fun authUser(login: AuthenticationRequest): String

    suspend fun getBonus(): String
}