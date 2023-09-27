package com.giftech.terbit.domain.usecase

import javax.inject.Inject

class GetWeeklyAsaqChartOptionsUseCase @Inject constructor(){
    
    operator fun invoke(): Pair<List<Int>, List<Int>> {
        val weekOptions = (1..4).toList()
        val dayOfWeekOptions = (1..7).toList()
        return Pair(weekOptions, dayOfWeekOptions)
    }
    
}