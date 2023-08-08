package com.giftech.terbit.data.source.local

import com.giftech.terbit.data.source.local.statics.ProgramData
import com.giftech.terbit.data.source.local.statics.model.ProgramEntity

class ProgramLocalDataSource() {
    
    fun getAll(): List<ProgramEntity> {
        return ProgramData.get()
    }
    
}