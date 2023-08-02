package com.giftech.terbit.data.utils

import com.giftech.terbit.data.model.Asaq
import com.giftech.terbit.ui.components.enums.HeroEnum
import com.giftech.terbit.ui.components.enums.TingkatAktivitasEnum

object DataProvider {
    fun asaqList():List<Asaq>{
        val asaqList = mutableListOf<Asaq>()
        for (i in 1..12) {
            val asaq = Asaq(
                id = i,
                hero = when (i) {
                    1 -> HeroEnum.Asaq1
                    2 -> HeroEnum.Asaq2
                    3 -> HeroEnum.Asaq3
                    4 -> HeroEnum.Asaq4
                    5 -> HeroEnum.Asaq5
                    6 -> HeroEnum.Asaq6
                    7 -> HeroEnum.Asaq7
                    8 -> HeroEnum.Asaq8
                    9 -> HeroEnum.Asaq9
                    10 -> HeroEnum.Asaq10
                    11 -> HeroEnum.Asaq11
                    12 -> HeroEnum.Asaq12
                    else -> HeroEnum.Asaq1 // Default case, you can adjust this as per your requirement.
                },
                tingkatHariKerja = TingkatAktivitasEnum.DEFAULT,
                tingkatHariLibur = TingkatAktivitasEnum.DEFAULT
            )
            asaqList.add(asaq)
        }
        return asaqList
    }


}