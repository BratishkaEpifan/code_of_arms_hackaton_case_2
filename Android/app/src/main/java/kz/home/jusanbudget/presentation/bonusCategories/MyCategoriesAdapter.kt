package kz.home.jusanbudget.presentation.bonusCategories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.home.jusanbudget.databinding.ItemMyCategoriesRvBinding
import kz.home.jusanbudget.domain.CategoryDaniyar

class MyCategoriesAdapter() : ListAdapter<CategoryDaniyar, MyCategoriesAdapter.MyCategoriesViewHolder>(MyCategoriesDiffUtilCallback()) {

    inner class MyCategoriesViewHolder(val binding: ItemMyCategoriesRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryDaniyar) {
            binding.tvCategoryName.text = item.categoryName
            binding.tvPossibleBonuses.text = item.possibleBonuses
            binding.ivCategoryIcon.setImageResource(item.categoryIcon)
            binding.tvCategoryBonus.text = item.categoryBonus
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCategoriesViewHolder {
        return MyCategoriesViewHolder(
            ItemMyCategoriesRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyCategoriesViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}

class CategoriesDiffUtilCallback : DiffUtil.ItemCallback<CategoryDaniyar>() {
    override fun areItemsTheSame(oldItem: CategoryDaniyar, newItem: CategoryDaniyar): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryDaniyar, newItem: CategoryDaniyar): Boolean {
        return oldItem == newItem
    }
}