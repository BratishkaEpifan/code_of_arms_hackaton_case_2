package kz.home.jusanbudget.presentation.analytics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kz.home.jusanbudget.R
import kz.home.jusanbudget.databinding.FragmentFutureBinding
import kz.home.jusanbudget.databinding.FragmentNewBinding

class FutureFragment : Fragment() {

    private var _binding: FragmentFutureBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFutureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddCategory.setOnClickListener {

        }
    }
}