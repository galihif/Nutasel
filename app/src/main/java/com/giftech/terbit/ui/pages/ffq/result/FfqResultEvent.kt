package com.giftech.terbit.ui.pages.ffq.result

sealed class FfqResultEvent {

    data class Init(
        val programId: Int,
    ) : FfqResultEvent()
    
}