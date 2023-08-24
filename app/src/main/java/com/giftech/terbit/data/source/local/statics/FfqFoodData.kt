package com.giftech.terbit.data.source.local.statics

import com.giftech.terbit.data.source.local.room.entity.FfqFoodEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FfqFoodData @Inject constructor(){
    
    fun getAll(): List<FfqFoodEntity> {
        val list = mutableListOf<FfqFoodEntity>()
        for (i in foodIdList.indices) {
            list.add(
                FfqFoodEntity(
                    foodId = foodIdList[i],
                    foodCategoryId = categoryIdList[i],
                    name = nameList[i],
                )
            )
        }
        return list
    }
    
    private val foodIdList = listOf(
        1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010, // Staple
        1011, 1012, 1013, 1014, 1015, 1016, // Animal protein
        1017, 1018, 1019, 1020, 1021, // Vegetable protein
        1022, 1023, 1024, 1025, // Vegetable
        1026, 1027, 1028, 1029, 1030, 1031, // Fruit
        1032, 1033, 1034, 1035, // Drink
        1036, 1037, 1038, // Snack
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