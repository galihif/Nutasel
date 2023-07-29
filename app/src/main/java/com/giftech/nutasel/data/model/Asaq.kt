package com.giftech.nutasel.data.model

import com.giftech.nutasel.ui.components.enums.HeroEnum
import com.giftech.nutasel.ui.components.enums.TingkatAktivitasEnum

data class Asaq(
    var id:Int,
    var hero:HeroEnum,
    var tingkatHariKerja:TingkatAktivitasEnum,
    var tingkatHariLibur:TingkatAktivitasEnum,
)
