package com.giftech.terbit.data.repository

import android.util.Log
import com.giftech.terbit.data.mapper.AsaqMapper
import com.giftech.terbit.data.source.local.room.dao.AsaqDao
import com.giftech.terbit.data.source.local.statics.util.AsaqTestType
import com.giftech.terbit.domain.enums.SedenterType
import com.giftech.terbit.domain.model.Asaq
import com.giftech.terbit.domain.repository.IAsaqRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class AsaqRepository @Inject constructor(
    private val asaqDao: AsaqDao,
    private val mapper: AsaqMapper,
) : IAsaqRepository {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getPreTestAsaq(): Flow<List<Asaq>> =
        asaqDao.getPreTests().mapLatest { mapper.mapToDomain(it) }
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getPostTestAsaq(): Flow<List<Asaq>> =
        asaqDao.getPostTest().mapLatest { mapper.mapToDomain(it) }
    
    override suspend fun getSedenterType(): Flow<SedenterType> = flow {
        val score = asaqDao.getPreTests().first().sumOf {
            it.durasiHariKerja + it.durasiHariLibur
        }
        val avgMinutesPerQuestion = score/12
        when {
            avgMinutesPerQuestion <= 59 -> emit(SedenterType.RINGAN)
            avgMinutesPerQuestion <= 119 -> emit(SedenterType.SEDANG)
            else -> emit(SedenterType.BERAT)
        }
    }

    override suspend fun getAsaqAverage(): Flow<Double> = flow{
        val totalMinutes = asaqDao.getPreTests().first().sumOf {
            (it.durasiHariKerja.toDouble() * 5 + it.durasiHariLibur.toDouble() * 2) / 7
        }
        val avgMinutesQuest = totalMinutes/12
        val avgHoursPerquestion = avgMinutesQuest/60
        Log.d("AsaqRepository", "totalMinutes: $totalMinutes")
        Log.d("AsaqRepository", "avgMinutesQuest: $avgMinutesQuest")
        Log.d("AsaqRepository", "avgHoursPerquestion: $avgHoursPerquestion")
        emit(avgHoursPerquestion.toDouble())
    }

    override suspend fun insertPreTestAsaq(asaq: List<Asaq>) {
        asaqDao.insertAll(mapper.mapToEntity(asaq, AsaqTestType.PRE_TEST))
    }
    
    override suspend fun insertPostTestAsaq(asaq: List<Asaq>) {
        asaqDao.insertAll(mapper.mapToEntity(asaq, AsaqTestType.POST_TEST))
    }
    
}