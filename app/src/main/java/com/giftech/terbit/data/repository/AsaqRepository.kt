package com.giftech.terbit.data.repository

import android.util.Log
import com.giftech.terbit.data.mapper.AsaqMapper
import com.giftech.terbit.data.source.local.room.dao.AsaqDao
import com.giftech.terbit.data.source.local.statics.util.AsaqTestType
import com.giftech.terbit.domain.enums.SedenterType
import com.giftech.terbit.domain.model.Asaq
import com.giftech.terbit.domain.repository.IAsaqRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AsaqRepository @Inject constructor(
    private val asaqDao: AsaqDao,
    private val mapper:AsaqMapper
):IAsaqRepository{
    override fun getPreTestAsaq(): Flow<List<Asaq>> =
        asaqDao.getPreTests().map { mapper.mapToDomain(it) }

    override fun getPostTestAsaq(): Flow<List<Asaq>> {
        TODO("Not yet implemented")
    }

    override fun getSedenterType(): Flow<SedenterType> = flow{
        val score = asaqDao.getPreTests().first().sumOf {
            it.durasiHariKerja + it.durasiHariLibur
        }
        when{
            score <= 708 -> emit(SedenterType.RINGAN)
            score <= 1428 -> emit(SedenterType.SEDANG)
            else -> emit(SedenterType.BERAT)
        }
        Log.d("GALIH", "getSedenterType repo: $score")
    }

    override suspend fun insertPreTestAsaq(asaq: List<Asaq>) {
        asaqDao.insertAll(mapper.mapToEntity(asaq,AsaqTestType.PRE_TEST))
    }

    override suspend fun insertPostTestAsaq(asaq: List<Asaq>) {
        asaqDao.insertAll(mapper.mapToEntity(asaq,AsaqTestType.POST_TEST))
    }
}