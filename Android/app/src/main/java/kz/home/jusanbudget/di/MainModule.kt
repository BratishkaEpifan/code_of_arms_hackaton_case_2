package kz.home.jusanbudget.di

import kz.home.jusanbudget.data.RepositoryImpl
import kz.home.jusanbudget.domain.Repository
import kz.home.jusanbudget.presentation.MyBankViewModel
import kz.home.jusanbudget.presentation.expenses.ExpensesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    viewModel { ExpensesViewModel(get()) }
    viewModel { MyBankViewModel(get()) }

    factory<Repository> { RepositoryImpl(get()) }
}

val modules = listOf(mainModule, networkModule)