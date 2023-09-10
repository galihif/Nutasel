package com.giftech.terbit.ui.pages.ffq.main

sealed class FfqMainEvent {
    
    data class Init(
        val programId: Int,
    ) : FfqMainEvent()
    
    data class CompleteFfq(
        val programId: Int,
    ) : FfqMainEvent()
    
}