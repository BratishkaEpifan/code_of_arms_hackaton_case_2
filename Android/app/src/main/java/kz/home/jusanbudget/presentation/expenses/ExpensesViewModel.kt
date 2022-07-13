package kz.home.jusanbudget.presentation.expenses

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kz.home.jusanbudget.domain.Repository

class ExpensesViewModel(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

}