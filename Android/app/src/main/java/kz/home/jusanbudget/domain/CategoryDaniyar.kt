package kz.home.jusanbudget.domain

import androidx.annotation.DrawableRes

data class CategoryDaniyar(
    var id:Int,
    val categoryName:String,
    val myBonuses:String,
    val possibleBonuses:String,
    @DrawableRes val categoryIcon: Int,
    val categoryBonus: String
)