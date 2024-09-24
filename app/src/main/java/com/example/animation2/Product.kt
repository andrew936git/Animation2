package com.example.animation2

import java.io.Serializable

class Product(val name: String, var price: Int, val image: Int): Serializable {
    companion object{
        val list = arrayListOf(
            Product("Крупа пшеничная Булгар", 97, R.drawable.bulgur),
            Product("M&M'S", 102, R.drawable.m_ms),
            Product("Маслины без косточки", 312, R.drawable.maslini),
            Product("Молочный шоколад Коркунов", 157, R.drawable.korkunov),
            Product("Кофе молотый Lavazza", 553, R.drawable.cofe_lavazza),
            Product("Перец Халлапеньо", 157, R.drawable.peper),
            Product("Чай черный Greenfield ", 640, R.drawable.greenffield_tea),
            Product("Чипсы Lays", 222, R.drawable.lays),
            Product("Рыбные нагетсы", 99, R.drawable.nugets),
            Product("Чиабатта с куриным филе", 139, R.drawable.sandvicht),
            Product("Кофейный напиток 3в1", 13, R.drawable.nescafe),
            Product("Эклеры с заварным кремом", 239, R.drawable.ekler),
            Product("Сушки Традиционные", 30, R.drawable.sushki),
            Product("Тараллини с чесноком", 56, R.drawable.tralini),
            Product("Хлеб Дачный нарезанный", 35, R.drawable.bread)
        )
    }
}