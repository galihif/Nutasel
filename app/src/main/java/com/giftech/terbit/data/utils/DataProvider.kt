package com.giftech.terbit.data.utils

import com.giftech.terbit.domain.model.Asaq
import com.giftech.terbit.presentation.util.Constants

object DataProvider {
    fun asaqList():List<Asaq>{
        val asaqList = mutableListOf<Asaq>()
        for (i in 1..Constants.TOTAL_ASAQ) {
            val asaq = Asaq(
                questionId = i,
                durasiHariKerja = 0,
                durasiHariLibur = 0
            )
            asaqList.add(asaq)
        }
        return asaqList
    }
}