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
        1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, // Staple
        1101, 1102, 1103, 1104, 1105, 1106, 1107, 1108, 1109, 1110, // Animal protein
        1201, 1202, 1203, 1204, // Vegetable protein
        1301, 1302, 1303, 1304, 1305, 1306, 1307, 1308, 1309, 1310, // Vegetable
        1401, 1402, 1403, 1404, 1405, 1406, 1407, // Fruit
        1501, 1502, 1503, 1504, // Drink
        1601, 1602, 1603, 1604, 1605, 1606, 1607, // Snack
    )
    
    private val categoryIdList = listOf(
        1, 1, 1, 1, 1, 1, 1, 1, 1, // Staple
        2, 2, 2, 2, 2, 2, 2, 2, 2, 2,// Animal protein
        3, 3, 3, 3, // Vegetable protein
        4, 4, 4, 4, 4, 4, 4, 4, 4, 4, // Vegetable
        5, 5, 5, 5, 5, 5, 5, // Fruit
        6, 6, 6, 6, // Drink
        7, 7, 7, 7, 7, 7, 7  // Snack
    )
    
    private val nameList = listOf(
        // Staple
        "Nasi Putih",
        "Roti Tawar Putih",
        "Mie kuning",
        "Bihun",
        "Mie Instan (Kuah)",
        "Mie Instan (Goreng)",
        "Soun",
        "Kentang",
        "Singkong",
        // Animal protein
        "Telur Ayam Ras",
        "Telur Bebek",
        "Dada Ayam",
        "Paha Ayam",
        "Daging Sapi",
        "Jeroan Sapi",
        "Ikan Lele",
        "Ikan Pindang",
        "Sosis",
        "Nugget Ayam",
        // Vegetable protein
        "Tempe Kedelai",
        "Tahu",
        "Kacang Hijau",
        "Kacang Merah",
        // Vegetable
        "Kangkung",
        "Bayam",
        "Kol",
        "Wortel",
        "Buncis",
        "Kacang Panjang",
        "Jamur Hitam",
        "Brokoli",
        "Sawi Hijau",
        "Sawi Putih",
        // Fruit
        "Pisang Muli",
        "Pisang Sunpride",
        "Pisang Tanduk",
        "Pepaya",
        "Semangka",
        "Apel",
        "Jeruk",
        // Drink
        "Teh",
        "Kopi Susu",
        "Kopi Hitam",
        "Susu Sapi",
        // Snack
        "Kerupuk Putih",
        "Kerupuk Kuning",
        "Keripik Singkong",
        "Keripik Kentang",
        "Bakwan",
        "Kroket",
        "Risoles",
    )
    
}