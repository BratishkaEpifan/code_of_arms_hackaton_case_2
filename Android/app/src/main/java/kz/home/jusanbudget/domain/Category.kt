package kz.home.jusanbudget.domain

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class Category(
    val id: Int,
    val name: String,
    val percent: Int,
    @DrawableRes val img: Int,
    var spent: Float,
    var bonuses: Float,
    var possibleBonus: Float,
    @ColorRes var color: Int,
    var proportions: Float
)