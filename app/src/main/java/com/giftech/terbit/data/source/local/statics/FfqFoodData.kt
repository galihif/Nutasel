package com.giftech.terbit.data.source.local.statics

import com.giftech.terbit.data.source.local.room.entity.FfqFoodEntity

object FfqFoodData {
    
    fun get(): List<FfqFoodEntity> {
        val list = mutableListOf<FfqFoodEntity>()
        for (i in foodIdList.indices) {
            list.add(
                FfqFoodEntity(
                    foodId = foodIdList[i],
                    categoryId = categoryIdList[i],
                    name = nameList[i],
                )
            )
        }
        return list
    }
    
    private val foodIdList = listOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, // Staple
        11, 12, 13, 14, 15, 16, // Animal protein
        17, 18, 19, 20, 21, // Vegetable protein
        22, 23, 24, 25, // Vegetable
        26, 27, 28, 29, 30, 31, // Fruit
        32, 33, 34, 35, // Drink
        36, 37, 38, // Snack
    )
    
    private val categoryIdList = listOf(
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, // Staple
        2, 2, 2, 2, 2, 2, // Animal protein
        3, 3, 3, 3, 3, // Vegetable protein
        4, 4, 4, 4, // Vegetable
        5, 5, 5, 5, 5, 5, // Fruit
        6, 6, 6, 6, // Drink
        7, 7, 7, // Snack
    )
    
    private val nameList = listOf(
        // Staple
        "Nasi",
        "Biskuit",
        "Jagung Segar",
        "Kentang",
        "Mie Basah",
        "Mie Kering",
        "Roti Putih",
        "Singkong",
        "Sukun",
        "Tape Beras Ketan",
        // Animal protein
        "Daging Sapi",
        "Daging Ayam",
        "Ikan Segar",
        "Ikan Teri Kering",
        "Telur Ayam",
        "Udang Basah",
        // Vegetable protein
        "Kacang Hijau",
        "Kacang Kedelai",
        "Kacang Merah",
        "Kacang Mete",
        "Tahu",
        // Vegetable
        "Bayam",
        "Kangkung",
        "Sawi",
        "Terong",
        // Fruit
        "Apel",
        "Pisang",
        "Mangga",
        "Nanas",
        "Semangka",
        "Melon",
        // Drink
        "Air Putih",
        "Es Teh",
        "Kopi Susu",
        "Matcha",
        // Snack
        "Gorengan",
        "Kerupuk",
        "Makanan Ringan (Keripik Berasa)",
    )
    
}