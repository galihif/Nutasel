package com.giftech.terbit.data.model

import com.giftech.terbit.ui.components.enums.HeroEnum
import com.giftech.terbit.ui.components.enums.TingkatAktivitasEnum

data class Asaq(
    var id:Int = 0,
    var hero:HeroEnum = HeroEnum.Asaq1,
    var tingkatHariKerja:TingkatAktivitasEnum = TingkatAktivitasEnum.DEFAULT,
    var tingkatHariLibur:TingkatAktivitasEnum = TingkatAktivitasEnum.DEFAULT,
)
