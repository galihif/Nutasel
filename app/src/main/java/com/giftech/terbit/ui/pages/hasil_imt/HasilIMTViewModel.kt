package com.giftech.terbit.ui.pages.hasil_imt

import androidx.lifecycle.ViewModel
import com.giftech.terbit.data.model.User
import com.giftech.terbit.ui.components.enums.KategoriIMTEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HasilIMTViewModel
@Inject constructor(

) : ViewModel() {

    private val _user = MutableStateFlow(User())
    fun setUser(user:User){
        _user.value = user
        calculateSkorIMT()
    }

    private val _skorIMT = MutableStateFlow(0.0)
    val skorIMT = _skorIMT
    private fun calculateSkorIMT(){
        val tinggi = (_user.value.tinggi.toDouble())/100
        val berat = _user.value.berat.toDouble()
        val skor = berat / (tinggi * tinggi)
        _skorIMT.value = skor
        setKategoriIMT()
    }

    private val _kategoriIMT = MutableStateFlow(KategoriIMTEnum.NORMAL)
    val kategoriIMT = _kategoriIMT
    private fun setKategoriIMT(){
        val skor = skorIMT.value
        when {
            skor < 17.0 -> {
                _kategoriIMT.value = KategoriIMTEnum.SANGAT_KURUS
            }
            skor < 18.5 -> {
                _kategoriIMT.value = KategoriIMTEnum.KURUS
            }
            skor < 25 -> {
                _kategoriIMT.value = KategoriIMTEnum.NORMAL
            }
            skor < 27 -> {
                _kategoriIMT.value = KategoriIMTEnum.GEMUK
            }
            else -> {
                _kategoriIMT.value = KategoriIMTEnum.OBESITAS
            }
        }
    }

}