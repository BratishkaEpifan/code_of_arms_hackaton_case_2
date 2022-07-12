package kz.home.jusanbudget.presentation.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.home.jusanbudget.domain.Repository

class ExpensesViewModel(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private var result = "0"

    fun getBonus(): String {

        viewModelScope.launch(ioDispatcher) {
            result = repository.getBonus()
        }
        return result
    }

}