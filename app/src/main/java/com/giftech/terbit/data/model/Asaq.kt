package com.giftech.terbit.data.model

import com.giftech.terbit.ui.components.enums.HeroEnum
import com.giftech.terbit.ui.components.enums.TingkatAktivitasEnum

data class Asaq(
    var id:Int,
    var hero:HeroEnum,
    var tingkatHariKerja:TingkatAktivitasEnum,
    var tingkatHariLibur:TingkatAktivitasEnum,
)
