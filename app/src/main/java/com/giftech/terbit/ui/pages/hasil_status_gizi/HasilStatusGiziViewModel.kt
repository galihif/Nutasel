package com.giftech.terbit.ui.pages.hasil_status_gizi

import androidx.lifecycle.ViewModel
import com.giftech.terbit.data.model.User
import com.giftech.terbit.ui.components.enums.KategoriStatusGiziEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HasilStatusGiziViewModel
@Inject constructor(

) : ViewModel() {
    private val _user = MutableStateFlow(User())
    fun setUser(user: User){
        _user.value = user
        calculateStatusGizi()
    }

    private val _skorStatusGizi = MutableStateFlow(0.0)
    val skorStatusGizi = _skorStatusGizi
    private fun calculateStatusGizi() {
        _skorStatusGizi.value = _user.value.skorIMT/_user.value.usia
        setKategoriStatusGizi()
    }

    private val _kategoriStatusGizi = MutableStateFlow(KategoriStatusGiziEnum.GIZI_BAIK)
    val kategoriStatusGizi = _kategoriStatusGizi
    private fun setKategoriStatusGizi(){
        val skor = skorStatusGizi.value
        when {
            skor < 0.9 -> {
                _kategoriStatusGizi.value = KategoriStatusGiziEnum.GIZI_BURUK
            }
            skor < 1.0 -> {
                _kategoriStatusGizi.value = KategoriStatusGiziEnum.GIZI_KURANG
            }
            skor < 1.3 -> {
                _kategoriStatusGizi.value = KategoriStatusGiziEnum.GIZI_BAIK
            }
            skor < 1.4 -> {
                _kategoriStatusGizi.value = KategoriStatusGiziEnum.GIZI_LEBIH
            }
            skor > 1.4 -> {
                _kategoriStatusGizi.value = KategoriStatusGiziEnum.OBESITAS
            }
        }
    }


}