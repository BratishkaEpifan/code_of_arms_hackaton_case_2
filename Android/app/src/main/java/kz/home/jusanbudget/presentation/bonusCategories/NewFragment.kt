package kz.home.jusanbudget.presentation.bonusCategories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kz.home.jusanbudget.R
import kz.home.jusanbudget.databinding.FragmentNewBinding
import kz.home.jusanbudget.domain.CategoryDaniyar
import kz.home.jusanbudget.presentation.expenses.ExpensesViewModel
import kz.home.jusanbudget.utils.categories
import kz.home.jusanbudget.utils.myCategories
import kz.home.jusanbudget.utils.otherCategories
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewFragment : Fragment() {

    private var idCounter = 0
    private var _binding: FragmentNewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoriesAdapter()
        binding.rvAllCategories.adapter = adapter
        adapter.submitList(otherCategories)

        val secondAdapter = MyCategoriesAdapter()
        binding.rvMyCategories.adapter = secondAdapter
        secondAdapter.submitList(myCategories)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}