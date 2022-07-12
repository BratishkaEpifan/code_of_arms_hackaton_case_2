package kz.home.jusanbudget.utils

import kz.home.jusanbudget.R
import kz.home.jusanbudget.domain.Category

val categories = mutableListOf(Category("Салоны красоты и косметики", 7, R.drawable.beauty , 5000F, 350F, R.color.yellow, 0F),
        Category("Одежда и обувь", 5, R.drawable.clothes, 0F, 0F, R.color.blue, 0F),
    Category("Кафе и рестораны", 5, R.drawable.food, 55000F, 2750F, R.color.mellon, 0F),
    Category("Путешествия", 5, R.drawable.travel, 20000F, 1000F, R.color.purple, 0F),
    Category("Онлайн кино и музыка", 15, R.drawable.music, 1000F, 150F, R.color.azure, 0F),
    Category("Игровые сервисы", 10, R.drawable.games, 0F, 0F, R.color.berry, 0F),
    Category("Медицинские услуги", 5, R.drawable.health, 2500F, 125F, R.color.salad, 0F),
    Category("Мебель", 5, R.drawable.furniture, 0F, 0F, R.color.coral, 0F),
    Category("Такси", 5, R.drawable.taxi, 10000F, 500F, R.color.orange, 0F),
    Category("Фитнес и SPA", 5, R.drawable.fitness, 25000F, 1250F, R.color.red, 0F),
    Category("Остальное", 2, R.drawable.others, 30000F, 600F, R.color.grey, 0F)
)