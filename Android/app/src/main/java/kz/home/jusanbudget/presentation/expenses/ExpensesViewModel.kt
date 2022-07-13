package kz.home.jusanbudget.presentation.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.home.jusanbudget.data.GamesResponse
import kz.home.jusanbudget.domain.Repository

class ExpensesViewModel(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    fun getAllBonus(): String {
        var x = ""
        viewModelScope.launch(ioDispatcher) {
            x = repository.getBonus()
        }
        return x
    }

    fun getGames(): GamesResponse{
        var c = GamesResponse(1,1, 1)
        viewModelScope.launch(ioDispatcher) {
            c = repository.getGames()
        }
        return c
    }
}