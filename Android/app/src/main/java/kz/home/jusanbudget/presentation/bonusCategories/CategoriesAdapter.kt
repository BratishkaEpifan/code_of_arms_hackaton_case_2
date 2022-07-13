package kz.home.jusanbudget.presentation.bonusCategories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.home.jusanbudget.databinding.ItemAllCategoriesRvBinding
import kz.home.jusanbudget.domain.CategoryDaniyar

class CategoriesAdapter() :
    ListAdapter<CategoryDaniyar, CategoriesAdapter.CategoriesViewHolder>(MyCategoriesDiffUtilCallback()) {

    inner class CategoriesViewHolder(val binding: ItemAllCategoriesRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryDaniyar) {
            binding.tvCategoryName.text = item.categoryName
            binding.tvMyBonuses.text = item.myBonuses
            binding.tvPossibleBonuses.text = item.possibleBonuses
            binding.ivCategoryIcon.setImageResource(item.categoryIcon)
            binding.tvCategoryBonus.text = item.categoryBonus
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            ItemAllCategoriesRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}

class MyCategoriesDiffUtilCallback : DiffUtil.ItemCallback<CategoryDaniyar>() {
    override fun areItemsTheSame(oldItem: CategoryDaniyar, newItem: CategoryDaniyar): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryDaniyar, newItem: CategoryDaniyar): Boolean {
        return oldItem == newItem
    }
}