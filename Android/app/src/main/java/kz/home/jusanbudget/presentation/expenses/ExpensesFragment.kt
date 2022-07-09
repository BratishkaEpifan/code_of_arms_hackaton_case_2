package kz.home.jusanbudget.presentation.expenses

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kz.home.jusanbudget.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExpensesFragment : Fragment(R.layout.fragment_expenses) {
    private val viewModel: ExpensesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}