package kz.home.jusanbudget.presentation.expenses

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import kz.home.jusanbudget.R
import kz.home.jusanbudget.domain.Category
import kz.home.jusanbudget.utils.categories
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExpensesFragment : Fragment(R.layout.fragment_expenses) {
    private val viewModel: ExpensesViewModel by viewModel()
    private lateinit var chart: PieChart
    private lateinit var recyclerView: RecyclerView
    var sum = 0F
    var bonus = 0F

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = setupRecyclerView(view)
        categoryAdapter.setItems(categories)

        calculateProportions()
        setupChart(view)
        chart.animateX(1000)
    }

    private fun setupChart(view: View) {
        chart = view.findViewById(R.id.chart1)
        chart.description.isEnabled = false
        chart.centerText = generateCenterText()
        chart.setCenterTextSize(10f)
        chart.holeRadius = 45f
        chart.transparentCircleRadius = 50f
        chart.legend.isEnabled = false
        chart.setUsePercentValues(true)
        chart.data = generatePieData()
    }

    private fun setupRecyclerView(view: View): CategoryAdapter {
        recyclerView = view.findViewById(R.id.categories_rv)
        val categoryAdapter = CategoryAdapter()
        val categoryManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        recyclerView.apply {
            adapter = categoryAdapter
            layoutManager = categoryManager
        }
        recyclerView.setHasFixedSize(true)
        return categoryAdapter
    }

    private fun calculateProportions() {
        for(i in categories.indices) {
            sum += categories[i].spent
            bonus += categories[i].bonuses
        }

        for(i in categories.indices) {
            categories[i].proportions = categories[i].spent / sum * 100
        }
    }

    private fun generateCenterText() : SpannableString {
        val s = SpannableString("$sum ₸ \n${bonus
            //viewModel.getBonus()
        } Б")
        s.setSpan(RelativeSizeSpan(2f), 0, 8, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 8, s.length, 0)
        return s
    }

    private fun generatePieData(): PieData? {
        val entries1: ArrayList<PieEntry> = ArrayList()
        val colors = mutableListOf<Int>()
        val temp = mutableListOf<Category>()
        for (i in categories.indices) {
            if(categories[i].proportions != 0F) {
                temp.add(categories[i])
            }
        }
        temp.sortBy { it.proportions }
        for (i in temp.indices) {
            entries1.add(PieEntry(temp[i].proportions))
            colors.add(resources.getColor(temp[i].color))
        }
        val ds1 = PieDataSet(entries1, "Budget")

        ds1.colors = colors
        ds1.sliceSpace = 2f
        ds1.valueTextColor = Color.WHITE
        ds1.valueTextSize = 12f
        val d = PieData(ds1)
        d.setValueFormatter(PercentFormatter(chart))
        return d
    }
}