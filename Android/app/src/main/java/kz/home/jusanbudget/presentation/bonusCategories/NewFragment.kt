package kz.home.jusanbudget.presentation.bonusCategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kz.home.jusanbudget.R
import kz.home.jusanbudget.databinding.FragmentNewBinding
import kz.home.jusanbudget.domain.CategoryDaniyar

class NewFragment : Fragment() {

    private var idCounter = 0
    private var _binding: FragmentNewBinding? = null
    private val binding get() = _binding!!
    private val category =
        CategoryDaniyar(idCounter++, "Travel", "+500₸", "500₸", R.drawable.beauty, "20% bonuses")

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
        adapter.submitList(MutableList(10) { category })

        val secondAdapter = MyCategoriesAdapter()
        binding.rvMyCategories.adapter = secondAdapter
        secondAdapter.submitList(MutableList(4) { category })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}