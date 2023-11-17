package com.giftech.terbit.data.source.local.statics

import com.giftech.terbit.R
import com.giftech.terbit.data.source.local.statics.model.FfqFoodCategoryEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FfqFoodCategoryData @Inject constructor(){
    
    fun getAll(): List<FfqFoodCategoryEntity> {
        val list = mutableListOf<FfqFoodCategoryEntity>()
        for (i in categoryIdList.indices) {
            list.add(
                FfqFoodCategoryEntity(
                    foodCategoryId = categoryIdList[i],
                    name = nameList[i],
                    abbreviation = abbreviation[i],
                    imageRes = imageList[i],
                )
            )
        }
        return list
    }
    
    // Increase id every new data
    private val categoryIdList = listOf(
        1,
        2,
        3,
        4,
        5,
        6,
        7,
    )
    
    private val nameList = listOf(
        "Makanan Pokok",
        "Lauk Hewani",
        "Lauk Nabati",
        "Sayuran",
        "Buah - buahan",
        "Minuman",
        "Selingan",
    )
    
    private val abbreviation = listOf(
        "MP",
        "LH",
        "LN",
        "SY",
        "BU",
        "MI",
        "SE",
    )
    
    private val imageList = listOf(
        R.drawable.img_staple_ffq_480_370,
        R.drawable.img_animal_protein_ffq_480_370,
        R.drawable.img_vegetable_protein_ffq_480_370,
        R.drawable.img_vegetable_ffq_480_370,
        R.drawable.img_fruit_ffq_480_370,
        R.drawable.img_beverage_ffq_480_370,
        R.drawable.img_snack_ffq_480_370,
    )
    
}