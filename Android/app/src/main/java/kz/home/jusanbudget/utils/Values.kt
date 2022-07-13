package kz.home.jusanbudget.utils

import kz.home.jusanbudget.R
import kz.home.jusanbudget.domain.Category

val categories = mutableListOf(Category(1, "Салоны красоты и косметики", 7, R.drawable.beauty , 5000F, 350F, 0F, R.color.yellow, 0F),
        Category(2, "Одежда и обувь", 5, R.drawable.clothes, 0F, 0F, 0F, R.color.blue, 0F),
    Category(3, "Кафе и рестораны", 5, R.drawable.food, 55000F, 2750F, 0F, R.color.mellon, 0F),
    Category(4, "Путешествия", 5, R.drawable.travel, 20000F, 1000F, 0F, R.color.purple, 0F),
    Category(5, "Онлайн кино и музыка", 15, R.drawable.music, 1000F, 150F, 0F, R.color.azure, 0F),
    Category(6, "Игровые сервисы", 10, R.drawable.games, 0F, 0F, 0F, R.color.berry, 0F),
    Category(7, "Медицинские услуги", 5, R.drawable.health, 2500F, 125F, 0F, R.color.salad, 0F),
    Category(8, "Мебель", 5, R.drawable.furniture, 0F, 0F, 0F, R.color.coral, 0F),
    Category(9, "Такси", 5, R.drawable.taxi, 10000F, 500F, 0F, R.color.orange, 0F),
    Category(10, "Фитнес и SPA", 5, R.drawable.fitness, 25000F, 1250F, 0F, R.color.red, 0F),
    Category(11, "Остальное", 2, R.drawable.others, 30000F, 600F, 0F, R.color.grey, 0F)
)

val months = mutableListOf(
    Category(spent = 40000F),
    Category(spent = 70000F),
    Category(spent = 50000F),
    Category(spent = 50000F),
    Category(spent = 80000F),
    Category(spent = 50000F),
    Category(spent = 90000F),
    Category(spent = 60000F),
    Category(spent = 70000F),
    Category(spent = 40000F),
    Category(spent = 30000F)
)

val categoriesData = mutableListOf(Category(1, "Салоны красоты и косметики", 7, R.drawable.beauty , 5000F, 350F, 0F, R.color.yellow, 0F),
    Category(2, "Одежда и обувь", 5, R.drawable.clothes, 30000F, 0F, 0F, R.color.blue, 0F),
    Category(3, "Кафе и рестораны", 5, R.drawable.food, 5000F, 2750F, 0F, R.color.mellon, 0F),
    Category(4, "Путешествия", 5, R.drawable.travel, 20000F, 1000F, 0F, R.color.purple, 0F),
    Category(5, "Онлайн кино и музыка", 15, R.drawable.music, 41000F, 150F, 0F, R.color.azure, 0F)
)
