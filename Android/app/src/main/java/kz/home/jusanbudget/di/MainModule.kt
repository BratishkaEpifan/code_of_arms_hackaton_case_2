package kz.home.jusanbudget.di

import kz.home.jusanbudget.data.RepositoryImpl
import kz.home.jusanbudget.domain.Repository
import org.koin.dsl.module

val mainModule = module {

    factory<Repository> { RepositoryImpl(get()) }
}

val modules = listOf(mainModule, networkModule)