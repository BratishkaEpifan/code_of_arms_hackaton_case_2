package kz.home.jusanbudget.domain

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

class Category(
    val name: String,
    val percent: Int,
    @DrawableRes val img: Int,
    var spent: Float,
    var bonuses: Float,
    @ColorRes var color: Int,
    var proportions: Float
)